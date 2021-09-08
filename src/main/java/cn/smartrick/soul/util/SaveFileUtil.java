//package cn.smartrick.soul.util;
//
//
//import org.apache.tomcat.util.http.fileupload.FileUtils;
//
//import javax.servlet.ServletContext;
//import java.io.File;
//import java.io.IOException;
//
//public class SaveFileUtil {
//	//action保存文件的工具方法
//	public static String saveFile(File file,String fileName,String pathStr){
//		//通过Action的ServletActionContext来获取到ServletContext(项目的上下文)
//		ServletContext application=ServletActionContext.getServletContext();
//		//通过application获取文件夹的绝对路径
//		String path=application.getRealPath(FilePath.SERVERPATH+pathStr);
//		//创建该文件对象，并且如果这个文件夹不存在那么创建
//		File pathFile=new File(path);
//		if(!pathFile.exists()){
//			pathFile.mkdirs();
//		}
//		//得到文件的后缀名
//		int lastIndex=fileName.lastIndexOf(".");
//		String lastName=fileName.substring(lastIndex);
//		//替换文件名字为当前系统的毫秒数时间
//		String newFileName=System.currentTimeMillis()+lastName;
//		//创建目标文件对象
//		File dir=new File(path+"/"+newFileName);
//		//确定备份文件夹的路径
//		String backupPath=FilePath.BACKPATH;
//		File backupFile=new File(backupPath+pathStr);
//		if(!backupFile.exists()){
//			//如果该文件夹不存在就创建该文件夹
//			backupFile.mkdirs();
//		}
//		if(file!=null){
//			try {
//				//通过File工具类将源文件对象拷贝到目标“文件对象”
//				FileUtils.copyFile(file, dir);
//				//将源文件对象拷贝到备份“文件夹”
//				FileUtils.copyDirectory(pathFile, backupFile);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else{
//			System.err.println("文件为空");
//		}
//		return newFileName;
//	};
//
//	//静默保存文件的工具方法
//	public static String saveFile(File file,String pathStr){
//		String path=FilePath.BACKPATH+pathStr;
//		//创建该文件对象，并且如果这个文件夹不存在那么创建
//		File pathFile=new File(path);
//		if(!pathFile.exists()){
//			pathFile.mkdir();
//		}
//		//得到文件的后缀名
//		String fileName=file.getName();
//		int lastIndex=fileName.lastIndexOf(".");
//		String lastName=fileName.substring(lastIndex);
//		//替换文件名字为当前系统的毫秒数时间
//		String newFileName=System.currentTimeMillis()+lastName;
//		//创建目标文件对象
//		File dir=new File(path+"/"+newFileName);
//		//确定备份文件夹的路径
////		String backupPath="F:"+pathStr;
////		File backupFile=new File(backupPath);
////		if(!backupFile.exists()){
////			//如果该文件夹不存在就创建该文件夹
////			backupFile.mkdir();
////		}
//		if(file!=null){
//			try {
//				//通过File工具类将源文件对象拷贝到目标“文件对象”
//				FileUtils.copyFile(file, dir);
//				//将源文件对象拷贝到备份“文件夹”
////				FileUtils.copyDirectory(pathFile, backupFile);
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}else{
//			System.err.println("文件为空,保存失败");
//		}
//		return newFileName;
//	};
//}
