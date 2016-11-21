package com.zcb.codegenerator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jdom.Element;


import com.zcb.codegenerator.utils.XmlUtils;

import freemarker.template.Configuration;
import freemarker.template.Template;

/**
 * 
 * @author 张呈彬
 * 源代码自动生成器
 */
public class SourceCodeGenerator {
	
	
	private static Map readCodeGenerateConfig(){
		String xmlDoc = XmlUtils.fileToString(getWebClassesPath()+"codeGenerator.xml", "utf-8");
		Element root = XmlUtils.parseXml(xmlDoc);
		Element serviceCode = root.getChild("serviceCode");
		Element serviceImplCode = root.getChild("serviceImplCode");
		Element controllerCode = root.getChild("controllerCode");
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("serviceCode", serviceCode);
		map.put("serviceImplCode", serviceImplCode);
		map.put("controllerCode", controllerCode);
		return map;
		/*String serviceTargetProject = context.getAttributeValue("serviceTargetProject");
		String serviceImplTargetProject = context.getAttributeValue("serviceImplTargetProject");
		
		System.out.println(serviceTargetProject);*/
	}
	

	/**
	 * 生成文件
	 * 
	 * @param templateName
	 *            模板文件
	 * @param fileName
	 *            生成文件
	 * @param root
	 *            参数
	 * @throws IOException 
	 */
	private static void buildFile(String templateName, String fileName, Map root) throws IOException {
		Configuration freemarkerCfg = new Configuration();
		//freemarkerCfg.setClassForTemplateLoading(SourceCodeGenerator.class, "/");
		freemarkerCfg.setDirectoryForTemplateLoading(new File(getWebClassesPath()+"template"+File.separator));
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		Template template;
		try {
			template = freemarkerCfg.getTemplate(templateName);
			template.setEncoding("UTF-8");
			File javaFile = new File(fileName);
			if(!javaFile.getParentFile().exists()) {  
	            //如果目标文件所在的目录不存在，则创建父目录  
	            System.out.println("目标文件所在目录不存在，准备创建它！");  
	            if(!javaFile.getParentFile().mkdirs()) {  
	                System.out.println("创建目标文件所在目录失败！");  	                 
	            }  
	        }  
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(javaFile), "UTF-8"));
			template.process(root, out);
			out.flush();
			System.out.println(templateName+"模板成功生成文件："+fileName);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 创建service模板
	 * @param map
	 * @throws IOException
	 */
	public static void service(Map map) throws IOException{
		Element serviceCode = (Element) map.get("serviceCode");
		
		Map<String,String> root = new HashMap<String, String>();
		String className=serviceCode.getAttributeValue("className")  ;
		String serviceTargetProject=serviceCode.getAttributeValue("serviceTargetProject");  
		String packageName=serviceCode.getAttributeValue("packageName");
		String targetDir=serviceCode.getAttributeValue("targetDir")  ;
		String classPackage=serviceCode.getAttributeValue("classPackage");
		String entityNameCN=serviceCode.getAttributeValue("entityNameCN");
		String author=serviceCode.getAttributeValue("author");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		root.put("className",className );
		root.put("classPackage",classPackage );
		root.put("packageName",packageName );
		root.put("entityNameCN",entityNameCN );
		root.put("author",author );
		root.put("date",date );
		String tempName = "service.ftl";
		String fileName = getWorkspace()+serviceTargetProject+"/"+targetDir+"/"+packageName.replaceAll("\\.", "/")+"/"+className+"Service.java";
		buildFile(tempName, fileName, root);
	}
	/**
	 * 创建serviceImpl模板
	 * @param map
	 * @throws IOException
	 */
	public static void serviceImpl(Map map) throws IOException{
		Element serviceImplCode = (Element) map.get("serviceImplCode");
		
		Map<String,String> root = new HashMap<String, String>();
		String className=serviceImplCode.getAttributeValue("className");
		String serviceImplTargetProject=serviceImplCode.getAttributeValue("serviceImplTargetProject");  
		String packageName=serviceImplCode.getAttributeValue("packageName");
		String targetDir=serviceImplCode.getAttributeValue("targetDir")  ;
		String classPackage=serviceImplCode.getAttributeValue("classPackage");
		String entityNameCN=serviceImplCode.getAttributeValue("entityNameCN");
		String servicePackage=serviceImplCode.getAttributeValue("servicePackage");
		String mapperPakage=serviceImplCode.getAttributeValue("mapperPakage");
		String author=serviceImplCode.getAttributeValue("author");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		String classNameLitter = captureName(className);
		root.put("className",className );
		root.put("classNameLitter",classNameLitter );
		root.put("classPackage",classPackage );
		root.put("packageName",packageName );
		root.put("servicePackage",servicePackage);
		root.put("mapperPakage",mapperPakage );
		root.put("entityNameCN",entityNameCN );
		root.put("author",author );
		root.put("date",date );
		String tempName = "serviceImpl.ftl";
		String fileName = getWorkspace()+serviceImplTargetProject+"/"+targetDir+"/"+packageName.replaceAll("\\.", "/")+"/"+className+"ServiceImpl.java";
		buildFile(tempName, fileName, root);
	}
	
	public static void controller(Map map) throws IOException{
		Element controllerCode = (Element) map.get("controllerCode");
		
		Map<String,String> root = new HashMap<String, String>();
		String className=controllerCode.getAttributeValue("className");
		String controllerTargetProject=controllerCode.getAttributeValue("controllerTargetProject");  
		String packageName=controllerCode.getAttributeValue("packageName");
		String targetDir=controllerCode.getAttributeValue("targetDir")  ;
		String classPackage=controllerCode.getAttributeValue("classPackage");
		String entityNameCN=controllerCode.getAttributeValue("entityNameCN");
		String servicePackage=controllerCode.getAttributeValue("servicePackage");
		String baseRequestMapping=controllerCode.getAttributeValue("baseRequestMapping");
		String controllerOtherName=controllerCode.getAttributeValue("controllerOtherName");
		if(StringUtils.isEmpty(controllerOtherName)){
			controllerOtherName = className;
		}
		String author=controllerCode.getAttributeValue("author");
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String date = format.format(new Date());
		String classNameLitter = captureName(className);
		root.put("className",className );
		root.put("classNameLitter",classNameLitter );
		root.put("classPackage",classPackage );
		root.put("packageName",packageName );
		root.put("servicePackage",servicePackage);
		root.put("baseRequestMapping",baseRequestMapping );
		root.put("entityNameCN",entityNameCN );
		root.put("controllerOtherName",controllerOtherName );
		root.put("author",author );
		root.put("date",date );
		String tempName = "controller.ftl";
		String fileName = getWorkspace()+controllerTargetProject+"/"+targetDir+"/"+packageName.replaceAll("\\.", "/")+"/"+className+"Controller.java";
		buildFile(tempName, fileName, root);
	}
	/**
	 * 获取classes目录
	 * @return
	 */
	public static  String getWebClassesPath(){
		String path = new SourceCodeGenerator().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
		if(StringUtils.isNotBlank(path)){
			if(path.startsWith("/")){
				path=path.substring(1);
			}
		}
		return path;
	}
	
	/**
	 * 获取工程目录，eclipse/myeclipse workspace目录
	 * @return
	 */
	public static String getWorkspace(){
		String porjPath = (String) System.getProperties().get("user.dir");
		String workSpace = porjPath.replace("daizhang-web", "");
		return workSpace;
	}
	
	/**
	 * 首字母变小写
	 * @param name
	 * @return
	 */
	public static String captureName(String name) {
		        char[] cs=name.toCharArray();
		        cs[0]+=32;
		        return String.valueOf(cs);		        
    }
	public static void main(String[] args) throws IOException {
		Map<String, Object> config = readCodeGenerateConfig();
		service(config);
		serviceImpl(config);
		controller(config);
	}
}
