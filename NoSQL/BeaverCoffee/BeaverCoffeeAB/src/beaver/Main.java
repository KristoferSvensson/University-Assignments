package beaver;


import java.util.ArrayList;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.MongoIterable;

/**
 * Mongo document database application
 * 
 * @author oskar, amar, kristofer, sara
 *
 */
public class Main {
	private MongoDatabase database;
	private Object stockId = "Malmoe_Sweden";

	public Main() {
		MongoClientURI uri = new MongoClientURI("mongodb+srv://Kristofer:T3ST!@cluster-ffxml.mongodb.net/test");
		MongoClient mongoClient = new MongoClient(uri);
		database = mongoClient.getDatabase("BeaverCoffee");
	}

	/**
	 * Adds an order to the database
	 * 
	 * @param brewedCoffee
	 * @param espresso
	 * @param latte
	 * @param cappuccino
	 * @param chocolate
	 * @param vanilla
	 * @param caramel
	 * @param irishCoffee
	 */
	public void addOrder(int brewedCoffee, int espresso, int latte, int cappuccino, int chocolate, int vanilla,
			int caramel, int irishCoffee, String clubId, String empID) {
		MongoCollection<Document> collection = database.getCollection("Orders");
		Document document = new Document();
		//To add data into database
//		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
//		DateTime dt = formatter.parseDateTime("22/05/2018");
		DateTime dt = new DateTime();
		document.put("date", dt.getDayOfMonth() + "/" + dt.getMonthOfYear() + "/" + dt.getYear() + " " + dt.getHourOfDay() + ":" + dt.getMinuteOfHour() + ":" + dt.getSecondOfMinute());
		
		if (brewedCoffee != 0) {
			document.put("brewedCoffee", brewedCoffee);
		}
		if (espresso != 0) {
			document.put("espresso", espresso);
		}
		if (latte != 0) {
			document.put("latte", latte);
		}
		if (cappuccino != 0) {
			document.put("cappuccino", cappuccino);
		}
		if (chocolate != 0) {
			document.put("chocolate", chocolate);
		}
		if (vanilla != 0) {
			document.put("vanilla", vanilla);
		}
		if (caramel != 0) {
			document.put("caramel", caramel);
		}
		if (irishCoffee != 0) {
			document.put("irishCoffee", irishCoffee);
		}

		if (!clubId.equals("")) {
			document.put("clubId", clubId);
		}
		document.put("employeeID", empID);
		Object[] object = { brewedCoffee, espresso, latte, cappuccino, chocolate, vanilla, caramel, irishCoffee, clubId};
		
		document.put("price", getPrice(object));

		collection.insertOne(document);
		reduceStock(stockId, brewedCoffee, espresso, latte, cappuccino, chocolate, vanilla, caramel, irishCoffee);
	}

	/**
	 * Gets the orders from the database
	 * 
	 * @return orders
	 */
	public ArrayList<Object[]> getOrders() {
		MongoCollection<Document> collection = database.getCollection("Orders");
		MongoIterable<Document> list = collection.find();
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		for (Document i : list) {
			Object[] object = { i.get("_id"), i.get("date"),i.get("brewedCoffee"), i.get("espresso"), i.get("latte"),
					i.get("cappuccino"), i.get("chocolate"), i.get("vanilla"), i.get("caramel"), i.get("irishCoffee"),
					i.get("clubId"), i.get("price")};
			arrayList.add(object);
		}

		return arrayList;

	}
	
	public ArrayList<Object[]> getOrdersFromDates(DateTime fromDate, DateTime toDate) {
		MongoCollection<Document> collection = database.getCollection("Orders");
		MongoIterable<Document> list = collection.find();
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dt;
		for (Document i : list) {
			dt = formatter.parseDateTime(i.get("date").toString());
			if(dt.isAfter(fromDate) && dt.isBefore(toDate)) {
				Object[] object = { i.get("_id"), i.get("date"),i.get("brewedCoffee"), i.get("espresso"), i.get("latte"),
						i.get("cappuccino"), i.get("chocolate"), i.get("vanilla"), i.get("caramel"), i.get("irishCoffee"),
						i.get("clubId"), i.get("price")};
				arrayList.add(object);
			}
		}

		return arrayList;

	}
	
	
	public ArrayList<Object[]> getOrdersFromDatesAndProduct(DateTime fromDate, DateTime toDate, ArrayList<String> productList) {
		MongoCollection<Document> collection = database.getCollection("Orders");
		BasicDBObject findQuery = new BasicDBObject();
		for(String item : productList) {
			findQuery.append(item, new BasicDBObject("$exists", true));
			
		}
		
		MongoIterable<Document> list = collection.find(findQuery);
		
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dt;
		for (Document i : list) {
			dt = formatter.parseDateTime(i.get("date").toString());
			if(dt.isAfter(fromDate) && dt.isBefore(toDate)) {
				Object[] object = { i.get("_id"), i.get("date"),i.get("brewedCoffee"), i.get("espresso"), i.get("latte"),
						i.get("cappuccino"), i.get("chocolate"), i.get("vanilla"), i.get("caramel"), i.get("irishCoffee"),
						i.get("clubId"), i.get("price")};
				arrayList.add(object);
			}
		}

		return arrayList;

	}
	
	public ArrayList<Object[]> getOrdersMadeByEmployee(DateTime fromDate, DateTime toDate, String employeeID) {
		MongoCollection<Document> collection = database.getCollection("Orders");
		BasicDBObject findQuery = new BasicDBObject();
		findQuery.put("employeeID", employeeID);
			
		
		MongoIterable<Document> list = collection.find(findQuery);
		
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH:mm:ss");
		DateTime dt;
		for (Document i : list) {
			dt = formatter.parseDateTime(i.get("date").toString());
			if(dt.isAfter(fromDate) && dt.isBefore(toDate)) {
				Object[] object = { i.get("_id"), i.get("date"),i.get("brewedCoffee"), i.get("espresso"), i.get("latte"),
						i.get("cappuccino"), i.get("chocolate"), i.get("vanilla"), i.get("caramel"), i.get("irishCoffee"),
						i.get("clubId"), i.get("price")};
				arrayList.add(object);
			}
		}

		return arrayList;

	}

	public ArrayList<Object[]> getEmployeeFromDate(DateTime fromDate, DateTime toDate) {
		MongoCollection<Document> collection = database.getCollection("Employees");
		MongoIterable<Document> list = collection.find();
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
		DateTime dt;
		DateTime dtEnd;
		for (Document i : list) {
			dt = formatter.parseDateTime(i.get("start").toString());
			dtEnd = formatter.parseDateTime(i.get("endDates").toString());
			if(dt.isAfter(fromDate) && dt.isBefore(toDate) && dtEnd.isAfter(fromDate) && dtEnd.isBefore(toDate)) {
				Object[] object = { i.get("_id"), i.get("name"), i.get("position"), i.get("start"),
						i.get("endDates"), i.get("fulltime")};
				arrayList.add(object);
			}
		}

		return arrayList;

	}
	
	public void deleteOrder(Object id) {
		MongoCollection<Document> collection = database.getCollection("Orders");

		BasicDBObject findQuery = new BasicDBObject();
		findQuery.put("_id", id);
		Document oldValues = collection.find(findQuery).first();
		int brewedCoffeeStock = 0, espressoStock = 0, latteStock = 0, cappucinoStock = 0, chocolateStock = 0,
				vanillaStock = 0, caramelStock = 0, irishCoffeeStock = 0;
		if (oldValues.getInteger("brewedCoffee") != null) {
			brewedCoffeeStock = -oldValues.getInteger("brewedCoffee");
		}
		if (oldValues.getInteger("espresso") != null) {
			espressoStock = -oldValues.getInteger("espresso");
		}
		if (oldValues.getInteger("latte") != null) {
			latteStock = -oldValues.getInteger("latte");
		}
		if (oldValues.getInteger("cappuccino") != null) {
			cappucinoStock = -oldValues.getInteger("cappuccino");
		}
		if (oldValues.getInteger("chocolate") != null) {
			chocolateStock = -oldValues.getInteger("chocolate");
		}
		if (oldValues.getInteger("vanilla") != null) {
			vanillaStock = -oldValues.getInteger("vanilla");
		}
		if (oldValues.getInteger("caramel") != null) {
			caramelStock = -oldValues.getInteger("caramel");
		}
		if (oldValues.getInteger("irishCoffee") != null) {
			irishCoffeeStock = -oldValues.getInteger("irishCoffee");
		}
		collection.deleteOne(new Document("_id", id));
		reduceStock(stockId, brewedCoffeeStock, espressoStock, latteStock, cappucinoStock, chocolateStock, vanillaStock,
				caramelStock, irishCoffeeStock);
	}

	/**
	 * Updates an order in the database
	 * 
	 * @param id
	 * @param brewedCoffee
	 * @param espresso
	 * @param latte
	 * @param cappuccino
	 * @param chocolate
	 * @param vanilla
	 * @param caramel
	 * @param irishCoffee
	 */
	public void updateOrder(Object id, int brewedCoffee, int espresso, int latte, int cappuccino, int chocolate,
			int vanilla, int caramel, int irishCoffee, Object clubId) {

		MongoCollection<Document> collection = database.getCollection("Orders");
		Bson filter = new Document().append("_id", id);

		Document newValue = new Document();

		BasicDBObject findQuery = new BasicDBObject();
		findQuery.put("_id", id);
		Document oldValues = collection.find(findQuery).first();
		int brewedCoffeeStock = 0, espressoStock = 0, latteStock = 0, cappucinoStock = 0, chocolateStock = 0,
				vanillaStock = 0, caramelStock = 0, irishCoffeeStock = 0;
		if (oldValues.getInteger("brewedCoffee") != null) {
			brewedCoffeeStock = compare(oldValues.getInteger("brewedCoffee"), brewedCoffee);
		}
		if (oldValues.getInteger("espresso") != null) {
			espressoStock = compare(oldValues.getInteger("espresso"), espresso);
		}
		if (oldValues.getInteger("latte") != null) {
			latteStock = compare(oldValues.getInteger("latte"), latte);
		}
		if (oldValues.getInteger("cappuccino") != null) {
			cappucinoStock = compare(oldValues.getInteger("cappuccino"), cappuccino);
		}
		if (oldValues.getInteger("chocolate") != null) {
			chocolateStock = compare(oldValues.getInteger("chocolate"), chocolate);
		}
		if (oldValues.getInteger("vanilla") != null) {
			vanillaStock = compare(oldValues.getInteger("vanilla"), vanilla);
		}
		if (oldValues.getInteger("caramel") != null) {
			caramelStock = compare(oldValues.getInteger("caramel"), caramel);
		}
		if (oldValues.getInteger("irishCoffee") != null) {
			irishCoffeeStock = compare(oldValues.getInteger("irishCoffee"), irishCoffee);
		}

		if (brewedCoffee != 0 || brewedCoffeeStock != 0) {
			newValue.append("brewedCoffee", brewedCoffee);
		}
		if (espresso != 0 || espressoStock != 0) {
			newValue.put("espresso", espresso);
		}
		if (latte != 0 || latteStock != 0) {
			newValue.put("latte", latte);
		}
		if (cappuccino != 0 || cappucinoStock != 0) {
			newValue.put("cappuccino", cappuccino);
		}
		if (chocolate != 0 || chocolateStock != 0) {
			newValue.put("chocolate", chocolate);
		}
		if (vanilla != 0 || vanillaStock != 0) {
			newValue.put("vanilla", vanilla);
		}
		if (caramel != 0 || caramelStock != 0) {
			newValue.put("caramel", caramel);
		}
		if (irishCoffee != 0 || irishCoffeeStock != 0) {
			newValue.put("irishCoffee", irishCoffee);
		}
		
		Object[] object = { brewedCoffee, espresso, latte, cappuccino, chocolate, vanilla, caramel, irishCoffee, clubId};
		newValue.put("price", getPrice(object));

		Bson updateOperationDocument = new Document("$set", newValue);
		collection.updateOne(filter, updateOperationDocument);
		reduceStock(stockId, brewedCoffeeStock, espressoStock, latteStock, cappucinoStock, chocolateStock, vanillaStock,
				caramelStock, irishCoffeeStock);
	}

	public int compare(int y, int x) {
		if (y > x) {
			return -(y - x);
		} else if (y < x) {
			return x - y;
		}
		return 0;
	}
	

	public void addEmployee(String name, String personnummer, String position, String start, String endDates,
			String fulltime) {
		MongoCollection<Document> collection = database.getCollection("Employees");
		Document document = new Document();
		if (!personnummer.equals("")) {
			document.put("_id", personnummer);
		}
		if (!name.equals("")) {
			document.put("name", name);
		}
		if (!position.equals("")) {
			document.put("position", position);
		}
		if (!start.equals("")) {
			document.put("start", start);
		}
		if (!endDates.equals("")) {
			document.put("endDates", endDates);
		}
		if (!fulltime.equals("")) {
			document.put("fulltime", fulltime);
		}

		collection.insertOne(document);
	}

	public ArrayList<Object[]> getEmployees() {
		MongoCollection<Document> collection = database.getCollection("Employees");
		MongoIterable<Document> list = collection.find();
		ArrayList<Object[]> arrayList = new ArrayList<Object[]>();
		for (Document i : list) {
			Object[] object = { i.get("_id"), i.get("name"), i.get("position"), i.get("start"),
					i.get("endDates"), i.get("fulltime") };
			arrayList.add(object);
		}

		return arrayList;

	}

	public void deleteEmployee(Object id) {
		MongoCollection<Document> collection = database.getCollection("Employees");
		collection.deleteOne(new Document("_id", id));
	}

	public void updateEmployee(Object id, String name, String personnummer, String position, String start,
			String endDates, String fulltime) {
		MongoCollection<Document> collection = database.getCollection("Employees");
		Bson filter = new Document().append("_id", id);

		Document newValue = new Document();
		if (!personnummer.equals("")) {
			newValue.put("_id", personnummer);
		}
		if (!name.equals("")) {
			newValue.put("name", name);
		}
		if (!position.equals("")) {
			newValue.put("position", position);
		}
		if (!start.equals("")) {
			newValue.put("start", start);
		}
		if (!endDates.equals("")) {
			newValue.put("endDates", endDates);
		}
		if (!fulltime.equals("")) {
			newValue.put("fulltime", fulltime);
		}

		Bson updateOperationDocument = new Document("$set", newValue);
		collection.updateOne(filter, updateOperationDocument);
	}
	
	public boolean checkIfEmployee(Object id) {
		MongoCollection<Document> collection = database.getCollection("Employees");
		BasicDBObject findQuery = new BasicDBObject();
		findQuery.put("_id", id);
		if (collection.find(findQuery).first() != null) {
			return true;
		}
		return false;
	}

	public boolean checkClub(Object id) {
		MongoCollection<Document> collection = database.getCollection("Club");
		BasicDBObject findQuery = new BasicDBObject();
		findQuery.put("_id", id + "_customerID");
		if (collection.find(findQuery).first() != null) {
			return true;
		}
		return false;
	}

	public void joinClub(String name, String personnummer, String address) {
		MongoCollection<Document> collection = database.getCollection("Club");
		Document document = new Document();
		if (!personnummer.equals("")) {
			document.put("_id", personnummer + "_customerID");
		}

		if (!name.equals("")) {
			document.put("name", name);
		}

		if (!address.equals("")) {
			document.put("address", address);
		}

		collection.insertOne(document);
	}

	public Object[] getCurrentStock() {
		MongoCollection<Document> collection = database.getCollection("Stock");
		MongoIterable<Document> list = collection.find();
		Document i = list.first();
		Object[] object = { i.get("_id"), i.get("brewedCoffee"), i.get("espresso"), i.get("latte"), i.get("cappuccino"),
				i.get("chocolate"), i.get("vanilla"), i.get("caramel"), i.get("irishCoffee") };

		return object;
	}

	public void reduceStock(Object id, int brewedCoffee, int espresso, int latte, int cappuccino, int chocolate,
			int vanilla, int caramel, int irishCoffee) {
		Object[] currentStock = getCurrentStock();
		updateStock(id, Integer.parseInt(currentStock[1].toString()) - brewedCoffee,
				Integer.parseInt(currentStock[2].toString()) - espresso,
				Integer.parseInt(currentStock[3].toString()) - latte,
				Integer.parseInt(currentStock[4].toString()) - cappuccino,
				Integer.parseInt(currentStock[5].toString()) - chocolate,
				Integer.parseInt(currentStock[6].toString()) - vanilla,
				Integer.parseInt(currentStock[7].toString()) - caramel,
				Integer.parseInt(currentStock[8].toString()) - irishCoffee);
	}

	public void updateStock(Object id, int brewedCoffee, int espresso, int latte, int cappuccino, int chocolate,
			int vanilla, int caramel, int irishCoffee) {
		MongoCollection<Document> collection = database.getCollection("Stock");
		Bson filter = new Document().append("_id", id);

		Document newValue = new Document();

		newValue.append("brewedCoffee", brewedCoffee);
		newValue.put("espresso", espresso);
		newValue.put("latte", latte);
		newValue.put("cappuccino", cappuccino);
		newValue.put("chocolate", chocolate);
		newValue.put("vanilla", vanilla);
		newValue.put("caramel", caramel);
		newValue.put("irishCoffee", irishCoffee);

		Bson updateOperationDocument = new Document("$set", newValue);
		collection.updateOne(filter, updateOperationDocument);
	}
	
	public double getPrice(Object[] order) {
		int brewedCoffee = 0, espresso = 0, latte = 0, cappucino = 0, chocolate = 0,
				vanilla = 0, caramel = 0, irishCoffee = 0;
		if(order[0] != null) {
			brewedCoffee = Integer.parseInt(order[0].toString());
		}
		if(order[1] != null) {
			espresso = Integer.parseInt(order[1].toString());
		}
		if(order[2] != null) {
			latte = Integer.parseInt(order[2].toString());
		}
		if(order[3] != null) {
			cappucino = Integer.parseInt(order[3].toString());
		}
		if(order[4] != null) {
			chocolate = Integer.parseInt(order[4].toString());
		}
		if(order[5] != null) {
			vanilla = Integer.parseInt(order[5].toString());
		}
		if(order[6] != null) {
			caramel = Integer.parseInt(order[6].toString());
		}
		if(order[7] != null) {
			irishCoffee = Integer.parseInt(order[7].toString());
		}
		
		
		double price = (brewedCoffee *10) + ((espresso + latte + cappucino + chocolate)*20) + ((vanilla + caramel + irishCoffee) * 10);
		
		if(checkIfEmployee(order[8])) {
			price = price * 0.90;
		}
		
		return price;
	}

	public static void main(String[] args) {
		
		Main main = new Main();
		GUI gui = new GUI(main);
	}
}
