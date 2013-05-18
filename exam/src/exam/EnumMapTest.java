package exam;
import java.util.EnumMap;
enum WeekEnum { 
Monday,Tuesday,Wednesday,Thursday,Friday,Saturday,Sunday 
}
public class EnumMapTest {
private EnumMap<WeekEnum, String> enumMap = new EnumMap<WeekEnum, String>(WeekEnum.class);
public EnumMapTest() { 
   enumMap.put(WeekEnum.Monday, "����һ��һ�ܿ�ʼ"); 
  enumMap.put(WeekEnum.Thursday, "���ڶ���һ�ܵڶ���"); 
  enumMap.put(WeekEnum.Wednesday, "���������úù�������"); 
  enumMap.put(WeekEnum.Thursday, "�����ģ�����"); 
  enumMap.put(WeekEnum.Friday, "�����壬����"); 
  enumMap.put(WeekEnum.Saturday, "������������"); 
  enumMap.put(WeekEnum.Sunday, "�����죬��Ϣһ�¡���"); 
}
public String getDayDescription(WeekEnum day) { 
  return this.enumMap.get(day); 
}
public static void main(String[] args) { 
       String dayDesc=new EnumMapTest().enumMap.get(WeekEnum.Sunday); 
       System.err.println(dayDesc); 
}
}
