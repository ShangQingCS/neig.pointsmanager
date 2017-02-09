package cn.sqhl.neig.pointsmanager.service.impl;

import java.math.BigDecimal;
import java.util.Date;
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
import cn.sqhl.neig.pointsmanager.mapper.NsOrderMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsUserMapper;
import cn.sqhl.neig.pointsmanager.mapper.NsUserPurseMapper;
import cn.sqhl.neig.pointsmanager.po.NsFreazesLog;
import cn.sqhl.neig.pointsmanager.po.NsGoods;
import cn.sqhl.neig.pointsmanager.po.NsOrder;
import cn.sqhl.neig.pointsmanager.po.NsOrderDetail;
import cn.sqhl.neig.pointsmanager.po.NsUser;
import cn.sqhl.neig.pointsmanager.po.NsUserPurse;
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
	
	@Autowired
	private NsOrderMapper nsOrderMapper; 
	
	@Autowired
	private NsUserMapper nsUserMapper; 
	
	@Autowired
	private NsUserPurseMapper nsUserPurseMapper;
	
	
	
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
		if(nsgoods.getSellnumb()==null || nsgoods.getSellnumb()==0){
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
	public Map freazesGoods(Long orderid,Long userid)  throws Exception{
		Map returninfo=new HashMap();
		
		Map map=new HashMap();
		map.put("orderid", orderid);
		map.put("userid", userid);
		map.put("status", "1");
		List list=nsOrderMapper.selectOrder(map);
		NsUser user=nsUserMapper.selectByPrimaryKey(userid);
		if(list.size()>0){
			NsOrder order=(NsOrder)list.get(0);
			BigDecimal total=order.getTotal();
			BigDecimal have=user.getUserKyBalance();
			BigDecimal now = have.subtract(total);
			if(now.doubleValue() < 0){
				returninfo.put("status", 1);
				returninfo.put("msg","可用余额不足");
				return returninfo;
			}else{
				user.setUserKyBalance(now);//更新用户可用余额
				
				order.setOrderstatus("2");//订单修改为已付款
				
				NsUserPurse purse=new NsUserPurse();//消费流水
				purse.setTradeType("1");
				purse.setTradeAmount(total);
				purse.setTradeSn(order.getName());
				purse.setTradeState("2");
				purse.setOptionType("1");
				purse.setUserAmount(now);
				purse.setUserId(user.getId());
				purse.setPurseType(0);
				purse.setPurseStatus(user.getUserStatus().toString());
				purse.setCreateTime(new Date());
				
				int k=nsUserMapper.updateByPrimaryKeySelective(user);
				int o=nsOrderMapper.updateByPrimaryKeySelective(order);
				int p=nsUserPurseMapper.insertSelective(purse);
				if(k<=0){
					log.log(Level.INFO, "更新用户 "+user.getUserName()+" 的可用余额出错");
					throw new RuntimeException("订单支付出错");
				}if(o<=0){
					log.log(Level.INFO, "更新订单 "+order.getId()+" 的状态出错");
					throw new RuntimeException("订单支付出错");
				}if(p<=0){
					log.log(Level.INFO, "记录用户 "+user.getUserName()+" 的订单： "+order.getId()+" 时保存消费流水出错");
					throw new RuntimeException("订单支付出错");
				}
			}
		}else{
			returninfo.put("status", 1);
			returninfo.put("msg","无对应待支付订单");
			return returninfo;
		}
		
	    List<NsOrderDetail>  odlist=nsOrderDetailMapper.queryOrderDetail(orderid);
		if(odlist!=null && odlist.size()>0){
			for(int i=0;i<odlist.size();i++){
				NsOrderDetail odt=odlist.get(i);
				if(odt!=null){					
					NsGoods goods=nsGoodsMapper.selectByPrimaryKey(odt.getGoodsid());
					int storenumb=goods.getSellnumb();//商品实际可销售库存
					if(storenumb >= odt.getCount()){
						goods.setSellnumb(storenumb-odt.getCount());
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
