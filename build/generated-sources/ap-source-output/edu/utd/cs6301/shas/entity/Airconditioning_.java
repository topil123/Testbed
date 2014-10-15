package edu.utd.cs6301.shas.entity;

import edu.utd.cs6301.shas.entity.Acsetting;
import java.math.BigInteger;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-15T01:08:56")
@StaticMetamodel(Airconditioning.class)
public class Airconditioning_ { 

    public static volatile SingularAttribute<Airconditioning, String> controlmode;
    public static volatile SingularAttribute<Airconditioning, String> acid;
    public static volatile SingularAttribute<Airconditioning, String> status;
    public static volatile SingularAttribute<Airconditioning, String> description;
    public static volatile SingularAttribute<Airconditioning, BigInteger> defaulttemperature;
    public static volatile CollectionAttribute<Airconditioning, Acsetting> acsettingCollection;

}