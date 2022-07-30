package com.probal.moddhobitto.api.expense.payload;

import com.probal.moddhobitto.core.expense.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserExpenseCategoryPayload implements Serializable {

    private String name;

    @NotNull
    private Long parentCategoryId;

    @NotNull
    private Type type;

    @NotNull
    private BigDecimal amount;

    private String description;

}
