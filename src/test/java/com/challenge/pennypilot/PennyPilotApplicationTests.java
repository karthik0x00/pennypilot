package com.challenge.pennypilot;

import com.challenge.pennypilot.splitwise.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@SpringBootTest
class PennyPilotApplicationTests {
	Group createGroup(int memberCount) {
		Group group = new Group();
		List<User> users = new ArrayList<>(memberCount);
		for (int i = 0; i < memberCount; i++) {
			User user = new User();
			user.setUserID(i);
			users.add(user);
		}
		group.setUsers(users);
		return group;
	}

	Map<Long, Double> getEqualSplitMap(double amount, int personCount) {
		double perPersonAmount = amount / personCount;
		Map<Long, Double> splitMap = new HashMap<>();
		for (long i = 0; i < personCount; i++) {
			splitMap.put(i, perPersonAmount);
		}
		return splitMap;
	}

	Transaction createEqualTransaction(Group group, double amount, long paidBy) {
		return new Transaction("test", paidBy, amount, group.getGroupID(), SplitStrategy.EQUAL, getEqualSplitMap(amount, group.getUsers().size()));
	}

	@Test
	void simpleEqualTransactionsWithSamePaidBy() {
		List<Transaction> transactions = new ArrayList<>();
		int memberCount = 3;
		long paidBy = 1;
		Group group = createGroup(memberCount);
		transactions.add(createEqualTransaction(group, 1200, paidBy));
		transactions.add(createEqualTransaction(group, 900, paidBy));
		group.setTransactions(transactions);
		Map<Long, List<Connection>> simplified = Algorithm.simplifyDebts(group);
		for (long key : simplified.keySet()) {
			if (key == 1) {
				List<Connection> connections = simplified.get(key);
				assertEquals(0, connections.size());
			} else {
				List<Connection> connections = simplified.get(key);
				assertEquals(1, connections.size());
				assertEquals(700, connections.get(0).getAmount());
			}
		}
	}

	@Test
	void simpleEqualTransactionsWithDifferentPaidBy() {
		List<Transaction> transactions = new ArrayList<>();
		int memberCount = 3;
		Group group = createGroup(memberCount);
		transactions.add(new Transaction("test", 0, 1200, 54, SplitStrategy.EQUAL, getEqualSplitMap(1200, memberCount)));
		transactions.add(new Transaction("test", 1, 900, 54, SplitStrategy.EQUAL, getEqualSplitMap(900, memberCount)));
		transactions.add(new Transaction("test", 2, 900, 54, SplitStrategy.EQUAL, getEqualSplitMap(900, memberCount)));
		transactions.add(new Transaction("test", 2, 100, 54, SplitStrategy.EQUAL, getEqualSplitMap(100, memberCount)));
		transactions.add(new Transaction("test", 0, 100, 54, SplitStrategy.EQUAL, getEqualSplitMap(100, memberCount)));
		group.setTransactions(transactions);
		Map<Long, List<Connection>> simplified = Algorithm.simplifyDebts(group);
	}
}
