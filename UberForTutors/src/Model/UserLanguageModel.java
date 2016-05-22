package Model;

public class UserLanguageModel {

	public int Id;
	public int LanguageId;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + LanguageId;
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
		if (!(obj instanceof UserLanguageModel)) {
			return false;
		}
		UserLanguageModel other = (UserLanguageModel) obj;
		if (LanguageId != other.LanguageId) {
			return false;
		}
		return true;
	}
	public int UserId;
}
