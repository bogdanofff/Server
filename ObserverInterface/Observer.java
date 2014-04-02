package ObserverInterface;

import java.util.List;

public interface Observer {
	public void update(String msg);
	public void update(List<String> userList);
}
