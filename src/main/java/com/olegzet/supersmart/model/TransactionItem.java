package com.olegzet.supersmart.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Zorin Oleg on 27.01.2021.
 */

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        property = "itemType")
@JsonSubTypes({
        @JsonSubTypes.Type(value = GreenItem.class, name = "green"),
        @JsonSubTypes.Type(value = UnitItem.class, name = "unit"),
        @JsonSubTypes.Type(value = WeightedItem.class, name = "weighted")
})

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class TransactionItem {
    private String id;
    private String barcode;
    private int weight;
}
