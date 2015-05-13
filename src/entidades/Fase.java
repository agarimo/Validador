package entidades;

import java.io.Serializable;

public class Fase
  implements Serializable, Comparable<Fase>
{
  String id;
  String empresaCon;
  String empresaSin;
  String particularCon;
  String particularSin;
  String art;

  public Fase(String id)
  {
    this.id = id;
  }

  public Fase(String id, String empresaC, String empresaS, String particularC, String particularS) {
    this.id = id;
    this.empresaCon = empresaC;
    this.empresaSin = empresaS;
    this.particularCon = particularC;
    this.particularSin = particularS;
  }

  public String getEmpresaCon() {
    return this.empresaCon;
  }

  public void setEmpresaCon(String empresaCon) {
    this.empresaCon = empresaCon;
  }

  public String getEmpresaSin() {
    return this.empresaSin;
  }

  public void setEmpresaSin(String empresaSin) {
    this.empresaSin = empresaSin;
  }

  public String getId() {
    return this.id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getParticularCon() {
    return this.particularCon;
  }

  public void setParticularCon(String particularCon) {
    this.particularCon = particularCon;
  }

  public String getParticularSin() {
    return this.particularSin;
  }

  public void setParticularSin(String particularSin) {
    this.particularSin = particularSin;
  }

  public boolean equals(Object obj)
  {
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    Fase other = (Fase)obj;
    if (this.id == null ? other.id != null : !this.id.equals(other.id)) {
      return false;
    }
    return true;
  }

  public int hashCode()
  {
    int hash = 3;
    hash = 17 * hash + (this.id != null ? this.id.hashCode() : 0);
    return hash;
  }

  public String toString()
  {
    return this.id;
  }

  public int compareTo(Fase o)
  {
    String a = this.id;
    String b = o.getId();
    return a.compareTo(b);
  }
}