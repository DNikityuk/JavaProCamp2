package org.procamp.model.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "material")
public class Material {

    private Long materialId;
    private String material;
    private String price;
    private String supplier;
    private String measurment;
    private String balance;


    public Material() {
    }


    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "material_seq")
    @SequenceGenerator(name="material_seq", sequenceName = "material_inst_id_seq", allocationSize = 1)
    @Column(name = "inst_id")
    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSupplier() {
        return supplier;
    }

    public void setSupplier(String supplier) {
        this.supplier = supplier;
    }

    public String getMeasurment() {
        return measurment;
    }

    public void setMeasurment(String measurment) {
        this.measurment = measurment;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Material)) return false;
        if ( obj == this) return true;

        Material other = (Material) obj;
        return this.materialId.equals(other.materialId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(materialId);
    }
}
