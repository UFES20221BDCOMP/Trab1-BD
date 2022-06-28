package ufes.trab1BD;
import static org.assertj.core.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.List;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ufes.trab1BD.models.Purchase;
import ufes.trab1BD.models.Store;
import ufes.trab1BD.models.User;
import ufes.trab1BD.repo.GeneralRepo;
import ufes.trab1BD.repo.PurchaseRepo;
import ufes.trab1BD.repo.StoreRepo;
import ufes.trab1BD.repo.TransferRepo;
import ufes.trab1BD.repo.UserRepo;



@SpringBootTest
class Trab1BdApplicationTests {
	@Autowired
	GeneralRepo generalRepo;

	@Autowired
	UserRepo userRepo;
	
	@Autowired
	StoreRepo storeRepo;

	@Autowired 
	PurchaseRepo purchaseRepo;

	@Autowired
	TransferRepo transferRepo;

	@Test
	@Order(1)
	public void testExistUsers(){
		List<User> list = userRepo.readUsers();
		assertThat(list).isNotNull();
	}
	
	@Test
	@Order(2)
	public void testNewUser(){
		List<User> list1 = userRepo.readUsers();
		int tam1 = list1.size();

		userRepo.createUser("New User", "145.312.123-84", "1999-12-31", 0);

		List<User> list2 = userRepo.readUsers();
		int tam2 = list2.size();

		assertThat(tam2).isGreaterThan(tam1);
	}

	@Test
	@Order(3)
	public void testDeleteLastUser(){
		List<User> list1 = userRepo.readUsers();
		int tam1 = list1.size();

		User user = list1.get(tam1 - 1);
		int id = user.getUser_id();
		userRepo.deleteUser(id);
	
		List<User> list2 = userRepo.readUsers();
		int tam2 = list2.size();
		assertThat(tam1).isGreaterThan(tam2);
	}

	@Test
	@Order(4)
	public void testCreatePurchase(){
		List<User> listUsers = userRepo.readUsers();
		List<Store> listStores = storeRepo.readStores();

		User user = listUsers.get(0);
		int user_id = user.getUser_id();

		Store store = listStores.get(0);
		int store_id = store.getStore_id();

		List<Purchase> listPurchases1 = purchaseRepo.readPurchases();
		int tam1 = listPurchases1.size();

		LocalDate localDate = LocalDate.now(ZoneId.of("America/Sao_Paulo"));
        LocalTime localTime = LocalTime.now(ZoneId.of("America/Sao_Paulo"));
        String date = localDate.toString() + " " + localTime.toString().substring(0, 8);

		purchaseRepo.createPurchase(user_id, store_id, 10, date);

		List<Purchase> listPurchases2 = purchaseRepo.readPurchases();
		int tam2 = listPurchases2.size();

		assertThat(tam2).isGreaterThan(tam1);
	}

	@Test
	@Order(5)
	public void testCancelPurchase(){
		List<Purchase> listPurchases = purchaseRepo.readPurchases();
		Purchase purchase = listPurchases.get(0);
		int purchase_id = purchase.getPurchase_id();

		User user = purchase.getUser();
		float saldoAntes = user.getBalance();

		purchaseRepo.cancelPurchase(purchase_id);

		float saldoDepois = user.getBalance();
		assertThat(saldoDepois).isGreaterThan(saldoAntes);
	}

	@Test
	@Order(6)
	public void testGetRichest(){
		List<Object[]> rich_list = generalRepo.findRichest();
		assertThat(rich_list).isNotNull();
	}

	@Test
	@Order(7)
	public void testGetMoney(){
		float app_money = generalRepo.readMoney();
		assertThat(app_money).isNotNull();
	}


}
