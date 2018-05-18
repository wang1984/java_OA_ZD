package cn.wang.oaA.annotation.test;

@ClassAnnotation(name="上海05期java就业班自从学习了项目以后就是牛")
public class SH05Java {
	@MethodAnnotation(name="马上有工作，月薪10K")
	public void java(){
		System.out.println("java");
	}
}
