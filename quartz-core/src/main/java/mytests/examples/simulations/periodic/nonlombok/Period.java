package mytests.examples.simulations.periodic.nonlombok;

import java.util.Date;

public class Period {

	public static class PeriodBuilder {

		private Date from;

		private int id;

		private Date to;

		PeriodBuilder () {
		}

		public Period build () {
			return new Period(id, from, to);
		}

		public PeriodBuilder from (Date from) {
			this.from = from;
			return this;
		}

		public PeriodBuilder id (int id) {
			this.id = id;
			return this;
		}

		public PeriodBuilder to (Date to) {
			this.to = to;
			return this;
		}

		public String toString () {
			return "Period.PeriodBuilder(id=" + this.id + ", from=" + this.from + ", to=" + this.to + ")";
		}
	}

	int id;
	Date from, to;

	Period (int id, Date from, Date to) {
		this.id = id;
		this.from = from;
		this.to = to;
	}

	public static PeriodBuilder builder () {
		return new PeriodBuilder();
	}

	protected boolean canEqual (final Object other) {
		return other instanceof Period;
	}

	public boolean equals (final Object o) {
		if (o == this) return true;
		if (!(o instanceof Period)) return false;
		final Period other = (Period)o;
		if (!other.canEqual((Object)this)) return false;
		if (this.getId() != other.getId()) return false;
		final Object this$from = this.getFrom();
		final Object other$from = other.getFrom();
		if (this$from == null ? other$from != null : !this$from.equals(other$from)) return false;
		final Object this$to = this.getTo();
		final Object other$to = other.getTo();
		if (this$to == null ? other$to != null : !this$to.equals(other$to)) return false;
		return true;
	}

	public Date getFrom () {
		return this.from;
	}

	public int getId () {
		return this.id;
	}

	public Date getTo () {
		return this.to;
	}

	public int hashCode () {
		final int PRIME = 59;
		int result = 1;
		result = result * PRIME + this.getId();
		final Object $from = this.getFrom();
		result = result * PRIME + ($from == null ? 43 : $from.hashCode());
		final Object $to = this.getTo();
		result = result * PRIME + ($to == null ? 43 : $to.hashCode());
		return result;
	}

	public void setFrom (Date from) {
		this.from = from;
	}

	public void setId (int id) {
		this.id = id;
	}

	public void setTo (Date to) {
		this.to = to;
	}

	public String toString () {
		return "Period(id=" + this.getId() + ", from=" + this.getFrom() + ", to=" + this.getTo() + ")";
	}
}
