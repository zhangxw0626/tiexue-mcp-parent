package com.tiexue.mcp.manage.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.aspectj.apache.bcel.classfile.Constant;
import org.aspectj.weaver.ast.Var;
import org.junit.runner.Request;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyEmitterReturnValueHandler;

import com.alibaba.fastjson.JSONObject;
import com.tiexue.mcp.core.dto.McpBaseInfoDto;
import com.tiexue.mcp.core.entity.McpBaseInfo;
import com.tiexue.mcp.core.service.IMcpBaseInfoService;
import com.tiexue.mcp.manage.dto.Paging;
@Controller
@RequestMapping("mcpbaseinfo")
public class McpBaseInfoController {
	//日志
	private static Logger logger=Logger.getLogger(McpBaseInfoController.class);
	
	@Resource
	IMcpBaseInfoService mcpBaseInfoService;
	
	private static final int psize=15;
	
	/**
	 * 获取基础信息列表
	 */
	@RequestMapping("/list")
	public String getbaseInfo(HttpServletRequest request,HttpServletResponse response){
		try {
			int pindex=1;
			String pindexStr=request.getParameter("pindex");
			if(pindexStr!=null&&!pindexStr.isEmpty()){
				pindex=Integer.parseInt(pindexStr);
			}
			int pStart=(pindex-1)*psize;
			int pcount=mcpBaseInfoService.getCount(" 1=1");
			//如果最后一页只有一条数据，则在删除时取上一页数据
			if(pStart>=pcount){
				pStart=pStart-psize;
			}
			List<McpBaseInfo> baseInfos= mcpBaseInfoService.getList("1=1",pStart,psize);
			Paging paging=new Paging();
			paging.setPcount(pcount);
			paging.setPsize(psize);
			paging.calcPtotalpages();
			if(pindex>paging.getPtotalpages()){
				pindex=paging.getPtotalpages();
			}
				paging.setPindex(pindex);
			request.setAttribute("baseInfos",baseInfos);
			request.setAttribute("paging",paging);
		} catch (Exception e) {
			logger.error("mcpbaseinfo/list error:"+e.getMessage());
		}
	
		return "mcpBaseInfo/list";
	}
	
	/**
	 * 新增或修改信息
	 */
	@RequestMapping("/add")
	public String addbaseInfo(HttpServletRequest request,HttpServletResponse response){
	    String cpidStr=request.getParameter("cpid");
	    String pindexStr=request.getParameter("pindex");
	    try {
	    	if(cpidStr!=null&&!cpidStr.isEmpty()){
		    	int cpid=Integer.parseInt(cpidStr);
		        McpBaseInfo mcpBaseInfo=mcpBaseInfoService.selectByPrimaryKey(cpid);
		    	request.setAttribute("mcpBaseInfo", mcpBaseInfo);
		    }
	    	request.setAttribute("pindex", pindexStr==null?"1":pindexStr);
		} catch (Exception e) {
			logger.error("mcpbaseinfo/list error:"+e.getMessage());
		}   
		return "mcpBaseInfo/add";
	}
	
	
	/**
	 * 保存数据
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/save",method=RequestMethod.POST)
    @ResponseBody
	public String saveData(HttpServletRequest request,HttpServletResponse response,McpBaseInfoDto mcpBaseInfoDto){
		JSONObject jObject=new JSONObject();
		McpBaseInfo baseInfo=new McpBaseInfo();
		try {
			if(mcpBaseInfoDto==null){
				jObject.put("ok",false);
				jObject.put("msg","数据为空");
				return jObject.toString();
			}
			
			if(mcpBaseInfoDto.getCpid()==null||mcpBaseInfoDto.getCpid()<=0){
				baseInfo=mcpBaseInfoDto.initMcpBaseInfo();
				int addNum= mcpBaseInfoService.insert(baseInfo);
				if(addNum>0){
					jObject.put("ok",true);
					jObject.put("msg", "保存成功");
				}
				else{
					jObject.put("ok",false);
					jObject.put("msg","保存失败");
				}
			}
			else{
				baseInfo=mcpBaseInfoService.selectByPrimaryKey(mcpBaseInfoDto.getCpid());
				baseInfo=mcpBaseInfoDto.initMcpBaseInfo(baseInfo);
				if(baseInfo==null){
					jObject.put("ok",false);
					jObject.put("msg","数据为空");
					return jObject.toString();
				}
				int updateNum= mcpBaseInfoService.updateByPrimaryKey(baseInfo);
				if(updateNum>0){
					jObject.put("ok",true);
					jObject.put("msg", "修改成功");
				}
				else{
					jObject.put("ok",false);
					jObject.put("msg","修改失败");
				}
			}
			
		} catch (Exception e) {
			logger.error("mcpbaseinfo/save error:"+e.getMessage());
			jObject.put("ok",false);
			jObject.put("msg","保存失败");
		}
		return jObject.toString();
	}
	/**
	 * 删除
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/deleteModel",method=RequestMethod.POST)
	@ResponseBody
	public String deleteModel(HttpServletRequest request,HttpServletResponse response){
		String cpidStr= request.getParameter("cpid");
		JSONObject jObject=new JSONObject();
		try {
			if(cpidStr!=null&&!cpidStr.isEmpty()){
				int cpid=Integer.parseInt(cpidStr);
				int deleteNum= mcpBaseInfoService.deleteByPrimaryKey(cpid);
				if(deleteNum>0){
					jObject.put("ok",true);
					jObject.put("msg", "删除成功");
				}
				else{
					jObject.put("ok",false);
					jObject.put("msg","删除失败");
				}
			}
		} catch (Exception e) {
			logger.error("mcpbaseinfo/save error:"+e.getMessage());
			jObject.put("ok",false);
			jObject.put("msg","删除失败");
		}
		return jObject.toJSONString();
	}
	

}