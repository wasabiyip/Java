package exam;
import java.util.EnumMap;
enum WeekEnum { 
Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday 
}
public class EnumMapTest {
private EnumMap<WeekEnum, String> enumMap = new EnumMap<WeekEnum, String>(WeekEnum.class);
public EnumMapTest() { 
   enumMap.put(WeekEnum.Monday, "星期一，一周开始"); 
  enumMap.put(WeekEnum.Thursday, "星期二，一周第二天"); 
  enumMap.put(WeekEnum.Wednesday, "星期三，好好工作……"); 
  enumMap.put(WeekEnum.Thursday, "星期四，……"); 
  enumMap.put(WeekEnum.Friday, "星期五，……"); 
  enumMap.put(WeekEnum.Saturday, "星期六，……"); 
  enumMap.put(WeekEnum.Sunday, "星期天，休息一下……"); 
}
public String getDayDescription(WeekEnum day) { 
  return this.enumMap.get(day); 
}
public static void main(String[] args) { 
       String dayDesc=new EnumMapTest().enumMap.get(WeekEnum.Sunday); 
       System.err.println(dayDesc); 
}
}
