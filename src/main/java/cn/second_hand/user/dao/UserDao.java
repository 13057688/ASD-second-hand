package cn.second_hand.user.dao;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.result.UpdateResult;

import cn.second_hand.user.domain.User;
import cn.second_hand.user.utils.MongoDBUtils;

public class UserDao {
	private MongoDatabase database = MongoDBUtils.getConnect();
	private MongoCollection<Document> collection = database.getCollection("customer");
	public void register(User user) {
		collection.insertOne(new Document("email",user.getEmail()).append("password", user.getPassword()).append("verifyCode", user.getVerifyCode()).append("activeStatus", false).append("saleApplyStatus", false));
	}

	public Document findByEmail(String email) {
		Bson filter = Filters.eq("email", email);
		FindIterable findIterable = collection.find(filter);
		Document document = (Document) findIterable.first();
		return document;
	}
	
	public Document findByVerifyCode(String code) {
		Bson filter = Filters.eq("verifyCode", code);
		FindIterable findIterable = collection.find(filter);
		Document document = (Document) findIterable.first();
		return document;
	}
	
	public void updateActiveState(String code,boolean state) {
		 collection.updateOne(Filters.eq("verifyCode", code), new Document("$set",new Document("activeStatus",state)));
	}

}
