package com.challenge.pennypilot.splitwise;

import java.util.*;

public class Algorithm {
    private static Map<Long, List<Connection>> constructTransactionGraph(Group group) {
        List<Transaction> transactions = group.getTransactions();
        Map<Long, List<Connection>> graph = new HashMap<>();
        List<User> users = group.getUsers();

        for (User user: users) {
            graph.put(user.getUserID(), new ArrayList<>());
        }

        for (Transaction transaction : transactions) {
            List<Long> userIDs =  new ArrayList<>(transaction.getSplitMap().keySet());
            SplitStrategy splitStrategy = transaction.getSplitStrategy();
            switch (splitStrategy) {
                case EQUAL: {
                    double individualAmount = transaction.getAmount() / userIDs.size();
                    long paidBy = transaction.getPaidBy();
                    for (long userID : userIDs) {
                        if (userID != paidBy) {
                            Connection connection = new Connection(paidBy, individualAmount);
                            graph.get(userID).add(connection);
                        }
                    }
                    break;
                }

                case PERCENTAGE: {
                    Map<Long, Double> splitMap = transaction.getSplitMap();
                    long paidBy = transaction.getPaidBy();
                    for (long userID: userIDs) {
                        if (userID != paidBy) {
                            double amountForUser = (splitMap.get(userID) / 100d) * transaction.getAmount();
                            Connection connection = new Connection(paidBy, amountForUser);
                            graph.get(userID).add(connection);
                        }
                    }
                    break;
                }

                case CUSTOM: {
                    long paidBy = transaction.getPaidBy();
                    for (long userID: userIDs) {
                        if (userID != paidBy) {
                            Map<Long, Double> splitMap = transaction.getSplitMap();
                            Connection connection = new Connection(paidBy, splitMap.get(userID));
                            graph.get(userID).add(connection);
                        }
                    }
                    break;
                }
            }
        }

        return graph;
    }

    private static void simplifyTransactionGraph(Map<Long, List<Connection>> graph) {
        List<Long> keys = new ArrayList<>(graph.keySet());
//      Merge all same directed connections into one
        for (long userID : keys) {
            List<Connection> connections = graph.get(userID);
            for (int i = 0; i < connections.size(); i++) {
                Connection currentConnection = connections.get(i);
                long currentUser = currentConnection.getToUserID();
                Iterator<Connection> nextConnectionsItr = connections.listIterator(i + 1);
                while (nextConnectionsItr.hasNext()) {
                    Connection nextConnection = nextConnectionsItr.next();
                    if (nextConnection.getToUserID() == currentUser) {
                        currentConnection.setAmount(currentConnection.getAmount() + nextConnection.getAmount());
                        nextConnectionsItr.remove();
                    }
                }
            }
        }

//      Merge all oppositely directed connections
        for (long userID : keys) {
            List<Connection> currentConnections = graph.get(userID);
            Iterator<Connection> connectionItr = currentConnections.iterator();
            while (connectionItr.hasNext()) {
                Connection connection = connectionItr.next();
                List<Connection> connectionOfConnection = graph.get(connection.getToUserID());
                Iterator<Connection> cocItr = connectionOfConnection.iterator();
                while (cocItr.hasNext()) {
                    Connection cocConnection = cocItr.next();
                    if (userID == cocConnection.getToUserID()) {
                        double connectionAmount = connection.getAmount();
                        double cocAmount = cocConnection.getAmount();
                        if (connectionAmount > cocAmount) {
                            connection.setAmount(connectionAmount - cocAmount);
                            cocItr.remove();
                        } else {
                            cocConnection.setAmount(cocAmount - connectionAmount);
                            connectionItr.remove();
                        }
                        break;
                    }
                }
            }
        }
    }

    private static Map<Long, List<Connection>> shortenPaths(Map<Long, List<Connection>> graph) {
//        TODO: Simplify graph
        return graph;
    }

    public static Map<Long, List<Connection>> simplifyDebts(Group group) {
        Map<Long, List<Connection>> transactionGraph = constructTransactionGraph(group);
        simplifyTransactionGraph(transactionGraph);
        return shortenPaths(transactionGraph);
    }
}
