package com.valverde.sporttrainerserver.user.dto;

import com.valverde.sporttrainerserver.user.entity.UserTrainingData;
import com.valverde.sporttrainerserver.user.enums.Country;
import com.valverde.sporttrainerserver.user.enums.Gender;
import com.valverde.sporttrainerserver.user.enums.MeterUnits;
import lombok.Data;
import org.modelmapper.ModelMapper;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class UserTrainingDataDTO {

    private Long id;

    private Date birthday;

    private String displayName;

    private Integer height;

    private Integer weight;

    private MeterUnits meterUnits;

    private Country country;

    private Gender gender;

    public static UserTrainingDataDTO toDTO(@NotNull UserTrainingData userTrainingData) {
        return new ModelMapper().map(userTrainingData, UserTrainingDataDTO.class);
    }

    public static UserTrainingData toEntity(@NotNull UserTrainingDataDTO trainingDataDTO) {
        return new ModelMapper().map(trainingDataDTO, UserTrainingData.class);
    }
}