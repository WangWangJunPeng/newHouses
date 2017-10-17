package com.sc.tradmaster.controller.shop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sc.tradmaster.bean.Project;
import com.sc.tradmaster.bean.Tag;
import com.sc.tradmaster.controller.BaseController;
import com.sc.tradmaster.service.house.HouseService;
import com.sc.tradmaster.service.shop.ShopService;
import com.sc.tradmaster.service.tagService.TagService;
import com.sc.tradmaster.utils.Page;

/**
 * 门店控制器
 * 
 * @author cdh 2017-07-05
 *
 */
@Controller
public class ShopController extends BaseController {

	@Resource(name = "shopService")
	private ShopService shopServie;

	@Resource(name = "houseService")
	private HouseService houseService;

	@Resource(name = "tagService")
	private TagService tagService;

	/**
	 * 进入门店地图页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/to_shop_map", method = RequestMethod.GET)
	public String toShopMapPage() {
		return "project/shopMap";
	}

	/**
	 * 将经纬度拆分并更新
	 */
	@RequestMapping(value = "/aerfawefaewfa", method = RequestMethod.GET)
	public void aerfawefae() {
		shopServie.updateSpitelngLat();
	}

	/**
	 * 获取城市内所有区的列表中的项目汇总信息
	 * 
	 * @param area
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_project_in_area", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public List<Map<String, Object>> getProjectInAreaInfo(String city) {

		String areaId = shopServie.findAddressByIpOrCityName(city, this.request, houseService);
		// 通过市获取所有区
		List<Map<String, String>> lmList = houseService.findCityAreaByShi(areaId);
		if (lmList.size() == 0) {// 存在市没有区
			Map<String, String> map = new HashMap<>();
			map.put("cityId", areaId);
			lmList.add(map);
		}
		return shopServie.findProjectListInArea(lmList);
	}

	/**
	 * 根据经纬度获取区域内的筛选的项目列表
	 * 
	 * @param maxLongitudes
	 * @param minLongitudes
	 * @param maxLatitudes
	 * @param minLatitudes
	 * @param tags
	 * @param houseTags
	 * @param projectId
	 * @param start
	 * @param limit
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_project_info", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getProjectsDataByGis(String maxLongitudes, String minLongitudes, String maxLatitudes,
			String minLatitudes, String pTags, String hTags, String projectId, Integer start, Integer limit) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		String[] tags = null;
		String[] houseTags = null;
		if (pTags != null && !"".equals(pTags)) {
			tags = pTags.split(",");
		}
		if (hTags != null && !"".equals(hTags)) {

			houseTags = hTags.split(",");
		}

		shopServie.findProjectListByAddressAndTag(maxLongitudes, minLongitudes, maxLatitudes, minLatitudes, tags,
				houseTags, projectId, page);

		return page;
	}

	@ResponseBody
	@RequestMapping(value = "/get_house_by_pro_tags", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public Object getHousesByProjectAndTags(String projectId, String[] houseTags, Integer start, Integer limit) {
		Page page = new Page();
		page.setStart(start);
		page.setLimit(limit);
		shopServie.findHouseList(projectId, houseTags, page, 1);
		return page;
	}

	/**
	 * 根据经纬度查找地图上存在的项目列表
	 * 
	 * @param maxLongitudes
	 * @param minLongitudes
	 * @param maxLatitudes
	 * @param minLatitudes
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_project_list_for_map", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public List<Project> getProjectListForMap() {
		return shopServie.findProjectListForMap();
	}

	/**
	 * 根据房源id查找选中的标签
	 * 
	 * @param houseNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/get_house_tags", method = RequestMethod.POST, produces = {
			"application/json;charset=UTF-8" })
	public List<Tag> getTagsByHouseNum(String houseNum) {
		return tagService.findTargetTag(houseNum);
	}
}
