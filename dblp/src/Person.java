import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

public class Person {
		private static Map<String, Person> personMap = new HashMap<String, Person>();
		private String name;
		private String urlpt;
		
		private boolean coauthorsLoaded;
		private Person coauthors[];
		static private SAXParser coauthorParser;
		static private CAConfigHandler coauthorHandler;
		static private List<Person>plist = new ArrayList<Person>();

		
		private Person(String name, String urlpt) {
			this.name = name;		
			this.urlpt = urlpt;
			personMap.put(name, this);
			coauthorsLoaded = false;
			labelvalid = false;
		}
		static public Person create(String name,String urlpt){
    	Person p;
    	p=searchPerson(name);
    	if(p==null)
    		p=new Person(name,urlpt);
    	return p;
    	
    }
	static public Person searchPerson(String name){
		return personMap.get(name);
	}
	
	static private class CAConfigHandler extends DefaultHandler{
		private String Value, urlpt;
		private boolean insideAuthor;
		public void startElement(String namspaceURI, 
				String localName, String rawName, Attributes atts)throws SAXException{
		if(insideAuthor = rawName.equals("author")){
			Value="";
			urlpt=atts.getValue("urlpt");
		}
		}
		public void endElement(String namespaceURI,
				String localName, String rawName)
				throws SAXException {
			if (rawName.equals("author") &&
				Value.length() > 0) {
				plist.add(create(Value, urlpt));
	}
			}
		
		public void characters(char[] ch, int start, int length)
		throws SAXException { if (insideAuthor)
		Value += new String(ch, start, length);
	}
		
		public void warning(SAXParseException e)
		throws SAXException { }
		public void error(SAXParseException e)
		throws SAXException {}
		public void fatalError(SAXParseException e)
		throws SAXException {}
	}
	//what is this?
	static {
		try {
			coauthorParser = SAXParserFactory.				
			newInstance().newSAXParser();
			coauthorHandler = new CAConfigHandler();
			coauthorParser.getXMLReader().setFeature("http://xml.org/sax/features/validation",false);
		} catch (ParserConfigurationException e) {} 
		catch (SAXException e) {}
	}
	//
	private void loadCoauthors() {
		if (coauthorsLoaded)
			return;
		plist.clear();
		try { 
			URL u = new URL("http://dblp.uni-trier.de/rec/pers/"+urlpt+"/xc");
			coauthorParser.parse(u.openStream(),coauthorHandler);
		}catch (IOException e) {}
		 catch (SAXException e) {}
		 coauthors = new Person[plist.size()];
		coauthors = plist.toArray(coauthors);
		coauthorsLoaded = true;
	}
	public Person[] getCoauthors() {
		if (!coauthorsLoaded) {
			loadCoauthors(); 
			}
		return coauthors;
	}
	private int label;
	private boolean labelvalid;
	public int getLabel() {
		return (!labelvalid)?0:label; 
	}
	public void resetLabel() {
		labelvalid = false; 
	}
	public boolean hasLabel() {
		return labelvalid; 
	}			
	public void setLabel(int label) {
		this.label = label;
		labelvalid = true;
	}
	static public void resetAllLabels() {
		Iterator<Person> i = personMap.values().iterator();
		while (i.hasNext()) {
			Person p = i.next();	
			p.labelvalid = false;
			p.label = 0;
		}
	}
		public String toString() { return name; }
	
	
	
	
	
}
