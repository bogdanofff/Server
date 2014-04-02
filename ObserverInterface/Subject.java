package ObserverInterface;


import java.util.List;

import Main.User;

public interface Subject {
	public void registerObserver(Observer o);
	public void removeObserver(Observer o);
	public void notifyObserver(String msg);
	public void updateListUsers(List<User> users);
}
