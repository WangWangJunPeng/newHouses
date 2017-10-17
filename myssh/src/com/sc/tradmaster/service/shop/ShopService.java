package com.sc.tradmaster.service.shop;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.sc.tradmaster.bean.House;
import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.Shops;
import com.sc.tradmaster.bean.User;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.utils.Page;

public interface ShopService {

	/**
	 * 进行门店注册
	 * 
	 * @param shop
	 * @return
	 */
	Integer addNewShop(Shops shop);

	/**
	 * 完善门店信息 包括经纬度
	 * 
	 * @param shop
	 * @param photoPic
	 * @param licensePic
	 * @return
	 */
	Integer addShopInfo(Shops shop, MultipartFile photoPic, MultipartFile licensePic);

	/**
	 * 根据shopId查找有没有注册过经纪人 如果已经注册 返回 1 没有注册 返回 0
	 * 
	 * @param shopId
	 * @param user
	 * @return
	 */
	Integer findShopAgentIsExist(User user);

	/**
	 * 查找市内的区列表(项目)
	 * 
	 * @param areas
	 * @return
	 */
	List<Map<String, Object>> findProjectListInArea(List<Map<String, String>> areas);

	/**
	 * 根据经纬度和标签查找项目 如果是选中了单个项目就直接返回一个项目
	 * 
	 * @param maxLongitudes
	 *            最大经度
	 * @param minLongitudes
	 *            最小经度
	 * @param maxLatitudes
	 *            最大纬度
	 * @param minLatitudes
	 *            最小纬度
	 * @param tags
	 *            项目标签
	 * @param houseTags
	 *            房源标签
	 * @param projectId
	 * 			获取单个项目
	 * @param page
	 *            分页对象
	 */
	void findProjectListByAddressAndTag(String maxLongitudes, String minLongitudes,
			String maxLatitudes, String minLatitudes, String[] tags, String[] houseTags, String projectId, Page page);

	/**
	 * 查找某一个项目的详细信息
	 * 
	 * @param projectId
	 * @return
	 */
	Map<String, Object> findOneProjectInfo(String projectId);

	/**
	 * 根据项目id查找房源 (上架并对中介可见)
	 * 
	 * @param projectId
	 * @param houseTags 房源标签 
	 * @param Page page
	 * @param status 查询的房源状态  1:可售房源  不传参：全部        
	 * @return
	 */
	void findHouseList(String projectId, String[] houseTags, Page page, Integer status);

	/**
	 * 将之前经纬度合并的分开并存到相应的字段中
	 */
	void updateSpitelngLat();
	
	
	/**
	 * 通过省市区id查找省市区名称
	 * @param shengOrShi
	 * @return
	 */
	String findCityNameById(String shengOrShi);
	
	/**
	 * 工具方法 如果city不为空就根据city超找城市id如果为空就根据ip地址查找
	 * @param city
	 * @return
	 */
	String findAddressByIpOrCityName(String city, HttpServletRequest request, HouseService houseService);
	
	/**
	 * 查找地图范围内的所有项目
	 * @param maxLongitudes
	 * @param minLongitudes
	 * @param maxLatitudes
	 * @param minLatitudes
	 * @return
	 */
	List<Project> findProjectListForMap();
}
