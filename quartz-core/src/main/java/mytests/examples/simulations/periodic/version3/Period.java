package mytests.examples.simulations.periodic.version3;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Period {
	int id;
	Date from, to;
}
