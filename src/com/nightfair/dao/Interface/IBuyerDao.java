package com.nightfair.dao.Interface;

import java.util.ArrayList;

import com.nightfair.vo.BuyerCollection;
import com.nightfair.vo.BuyerInfo;
import com.nightfair.vo.Collection;
import com.nightfair.vo.Nearby;

/**
 * 
 * @ClassName: IBuyerDao
 * @author debughao
 * @date 2015年10月16日
 */
public interface IBuyerDao {
	/**
	 * @Title getBuyerinfo
	 * @Description TODO(获取买家个人资料)
	 * @param user_id
	 *            买家用户id
	 * @return BuyerInfo 返回类型 买家实体
	 */
	public abstract BuyerInfo getBuyerinfo(int user_id);

	/**
	 * 
	 * @Title: updateBuyerHd
	 * 
	 * @Description: TODO(买家头像上传)
	 * @param image_url
	 *            图片路径
	 * @param user_id
	 *            买家用户id
	 * @return boolean 返回类型
	 */
	public abstract boolean updateBuyerHd(String image_url, int user_id);

	/**
	 * 
	 * @Title: updateBuyerInfo
	 * 
	 * @Description: TODO(买家头像更新个人资料)
	 * @param buyerInfo
	 *            买家实体类
	 * 
	 * @return boolean 返回类型
	 */
	public abstract boolean updateBuyerInfo(BuyerInfo buyerInfo);

	public abstract boolean insertBuyerInfo(BuyerInfo buyerInfo);

	public abstract BuyerInfo getBuyerInfoByphone(String phone);
	public abstract boolean isCollectionCoupon(Collection Collection, String way);
	public abstract ArrayList<BuyerCollection> selectCollection(int user_id);
	public abstract ArrayList<Nearby> selectAllShop();
}
