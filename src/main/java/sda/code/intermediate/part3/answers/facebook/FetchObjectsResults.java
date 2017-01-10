package sda.code.intermediate.part3.answers.facebook;

import com.restfb.Facebook;
import com.restfb.types.Page;
import com.restfb.types.User;

public class FetchObjectsResults {
	@Facebook
	public User me;

	@Facebook("koneserzyrozowychsloni")
	public Page page;
}
