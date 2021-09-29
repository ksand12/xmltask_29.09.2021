package createpark;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class Plant {
    private String type, name;
    private int coordX, coordY, height, 

    public Plant(String type, String name, int coordX, int coordY, int height) {
	this.type = type;
        this.name = name;
        this.coordX = coordX;
        this.coordY = coordY;
        this.height = height;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getcoordX() {
        return coordX;
    }
    public int getcoordY() {
        return coordY;
    }
    public int getheight() {
        return height;
    }
}


public class DOMParse {
    // Список для растений из XML файла
    private static ArrayList<Plant> plants = new ArrayList<>();

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        // Получение фабрики, чтобы после получить билдер документов.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

        // Получили из фабрики билдер, который парсит XML, создает структуру Document в виде иерархического дерева.
        DocumentBuilder builder = factory.newDocumentBuilder();

        // Распарсили XML, создав структуру Document. Теперь у нас есть доступ ко всем элементам, каким нам нужно.
        Document document = builder.parse(new File("Park.xml"));

	// Получение списка всех элементов employee внутри корневого элемента (getDocumentElement возвращает ROOT элемент XML файла).
        NodeList plantsElements = document.getDocumentElement().getElementsByTagName("plant");

        // Перебор всех элементов plant
        for (int i = 0; i < plantsElements.getLength(); i++) {
            Node tempplant = plantsElements.item(i);

            // Получение атрибутов каждого элемента
            NamedNodeMap attributes = tempplant.getAttributes();

            // Добавление сотрудника. Атрибут - тоже Node, потому нам нужно получить значение атрибута с помощью метода getNodeValue()
            plants.add(new Plant(attributes.getNamedItem("type").getNodeValue(),
				 attributes.getNamedItem("name").getNodeValue(),
				 attributes.getNamedItem("coordX").getNodeValue(),
				 attributes.getNamedItem("coordY").getNodeValue(),
				 attributes.getNamedItem("height").getNodeValue()
		));
        }

        // Вывод информации о каждом растении
        for (Plant tempplant1 : plants)
            System.out.println("Название " + tempplant1.getName() + "Тип " +  tempplant1.getType() + "Координата Х " +  tempplant1.getcoordX() + "Координата У " +  tempplant1.getcoordY() + "Высота " +  tempplant1.getheight() );
    
	//подсчёт количества растений и общей высоты
	int plantnumber = 0;
	int overallheight = 0;
	int numberoftrees = 0;
	int numberofbushes = 0;

	for (Plant tempplant1 : plants){
		plantnumber++;
		overallheight+=(int)tempplant1.getheight();
		if(tempplant1.getType() == "дерево"){
			numberoftrees++;
		}
		elseif(tempplant1.getType() == "куст"){
			numberofbushes++;
		}
	}
	//вывод количества растений
	System.out.println("Количество растений " + plantnumber + "Общая высота " + overallheight + "Число деревьев "+numberoftrees+"Число кустарников " +numberofbushes);

    }

	System.out.println("Парк посажен");
	
}


