package br.com.southsystem.cooperativismo.domain.dto;

import br.com.southsystem.cooperativismo.domain.enumerate.YesNoEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
public class ScheduleStatistics {

    private YesNoEnum option;
    private Long countVotes;

    @JsonIgnore
    private Long totalVotes;

    public ScheduleStatistics(YesNoEnum option, Long countVotes) {
        this.option = option;
        this.countVotes = countVotes;
    }

    public String getPercent(){
        BigDecimal percent = BigDecimal.valueOf(((countVotes.doubleValue() / totalVotes) * 100));
        percent = percent.setScale(2,  RoundingMode.HALF_EVEN);
        return new StringBuilder(percent.toString()).append("%").toString();
    }
}
