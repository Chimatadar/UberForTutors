package Model;

public class UserSkillRatingsModel {
	public int Id;
	public int UserId;
	public int SkillId;
	public int TotalPeople;
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + SkillId;
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
		if (!(obj instanceof UserSkillRatingsModel)) {
			return false;
		}
		UserSkillRatingsModel other = (UserSkillRatingsModel) obj;
		if (SkillId != other.SkillId) {
			return false;
		}
		return true;
	}
	public int RatingId;
	public byte Taught;
}
