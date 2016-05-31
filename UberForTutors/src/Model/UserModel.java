package Model;

public class UserModel {
	public int UserId;
	public String Password;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + UserId;
		return result;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserModel)) {
			return false;
		}
		UserModel other = (UserModel) obj;
		if (this.UserId != other.UserId) {
			return false;
		}
		return true;
	}
	public String Location;
	public String Email;
	public String UserName;
}
