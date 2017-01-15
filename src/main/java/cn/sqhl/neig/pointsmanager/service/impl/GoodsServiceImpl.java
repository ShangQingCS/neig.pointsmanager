package cn.sqhl.neig.pointsmanager.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sun.tools.classfile.InnerClasses_attribute.Info;

import cn.sqhl.neig.pointsmanager.controller.OrderController;
import cn.sqhl.neig.pointsmanager.mapper.NsFreazesLogMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsGoodsCategoryMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsGoodsMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsOrderDetailMapper;
import cn.sqhl.neig.pointsmanager.po.NsFreazesLog;
import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.service.BaseService;
import cn.sqhl.neig.pointsmanager.service.GoodsService;
import cn.sqhl.neig.pointsmanager.utils.PageCond;
import cn.sqhl.neig.pointsmanager.vo.Goods;
import cn.sqhl.neig.pointsmanager.vo.GoodsCategory;

@Service("goodsService")
public class GoodsServiceImpl implements GoodsService,BaseService{
	
	private Logger log=LogManager.getLogger(OrderController.class);

	@Autowired
	private NsGoodsMapper nsGoodsMapper;
	
	@Autowired
	private NsGoodsCategoryMapper nsGoodsCategoryMapper;
	
	@Autowired
	private NsOrderDetailMapper nsOrderDetailMapper;
	
	@Autowired
	private NsFreazesLogMapper nsFreazesLogMapper; 
	
	
	public List<Object> queryObj(Map<String, Object> map) {
		// TODO Auto-generated method stub
		return null;
	}

	
	public int addObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int removeObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public int updateObj(Object obj) {
		// TODO Auto-generated method stub
		return 0;
	}

	
	public Goods queryObj(Long id) {
		NsGoods nsgoods =nsGoodsMapper.selectByPrimaryKey(id);
		if(nsgoods!=null){
		Goods goods=new Goods(nsgoods.getId(),nsgoods.getGname(),
				nsgoods.getPrice(),nsGoodsCategoryMapper.selectByPrimaryKey(Integer.parseInt(nsgoods.getBrand()+"")).getCateName(), 
				nsgoods.getGoodimglist(), nsgoods.getGfullname(), 
				nsgoods.getStorenumb(), nsgoods.getGoodimg(), 
				nsgoods.getDetail(),nsgoods.getImg1(),nsgoods.getImg2(),
				nsgoods.getImg3(),nsgoods.getImg4(),nsgoods.getImg5(),nsGoodsCategoryMapper.selectByPrimaryKey(Integer.parseInt(nsgoods.getCategory()+"")).getCateName(),nsGoodsCategoryMapper.selectByPrimaryKey(Integer.parseInt(nsgoods.getKind()+"")).getCateName());
		if(nsgoods.getSellnumb()==0){
			goods.setStorenumb(0);
		}
		return goods;
		
		}else{
			return null;
		}
	}

	
	public List<Goods> queryPageByParentID(PageCond page, Object obj) {
		return nsGoodsMapper.queryListPageByParentID(page, (Map<String, Object>)obj);
	}
	
	public List<Goods> queryPageByLike(PageCond page, Object obj) {
		return nsGoodsMapper.queryListPageByLike(page, (Map<String, Object>)obj);
	}

	
	public List<GoodsCategory> queryGoodsCategory(PageCond page, Object obj) {
		return nsGoodsCategoryMapper.queryByParentId(page,(Map<String, Object>)obj);
	}

	
	public List<GoodsCategory> queryAllCategory() {
		return nsGoodsCategoryMapper.queryAll();
	}

	@Transactional
	public Map freazesGoods(Long orderid)  throws Exception{
		Map returninfo=new HashMap();
	    List<NsOrderDetail>  odlist=nsOrderDetailMapper.queryOrderDetail(orderid);
		if(odlist!=null && odlist.size()>0){
			for(int i=0;i<odlist.size();i++){
				NsOrderDetail odt=odlist.get(i);
				if(odt!=null){					
					NsGoods goods=nsGoodsMapper.selectByPrimaryKey(odt.getGoodsid());
					int storenumb=goods.getStorenumb();//商品实际可销售库存
					if(storenumb >= odt.getCount()){
						goods.setStorenumb(storenumb-odt.getCount());
						goods.setFreazes(goods.getFreazes()+odt.getCount());
						int k=nsGoodsMapper.updateByPrimaryKeySelective(goods);
						if(k>0){
							NsFreazesLog freaze=new NsFreazesLog();
							freaze.setGoodsid(Integer.parseInt(goods.getId()+""));
							freaze.setOrderid(Integer.parseInt(orderid+""));
							freaze.setStorenumb(storenumb);
							freaze.setStatus("0");
							int z=nsFreazesLogMapper.insertSelective(freaze);
							if(z>0){								
								continue;
							}else{
								log.log(Level.INFO, "冻结日志存储出错");
								throw new RuntimeException("冻结日志存储出错");
							}
						}else{
							log.log(Level.INFO, goods.getGname()+"("+goods.getId()+") 冻结出错");
							throw new RuntimeException("冻结出错");
						}
					}else{
						log.log(Level.INFO, goods.getGname()+"("+goods.getId()+") 商品库存不足~ 无法支持订单："+orderid+" 购买冻结~");
						throw new RuntimeException(goods.getGname()+"("+goods.getId()+") 商品库存不足~ ");
					}
				}else{
					log.log(Level.INFO, "订单存在无效详情项");
					throw new RuntimeException("orderdetail 存在无效项");
				}
			}
			returninfo.put("status", 0);
			returninfo.put("msg","所有商品成功冻结~");
		}else{
			log.log(Level.INFO, "订单不存在商品");
			throw new RuntimeException("无对应订单详情");
		}
		
		return returninfo;
	}
	
	
}
