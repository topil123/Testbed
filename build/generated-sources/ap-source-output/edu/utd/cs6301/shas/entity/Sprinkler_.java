package edu.utd.cs6301.shas.entity;

import edu.utd.cs6301.shas.entity.Sprinklersetting;
import javax.annotation.Generated;
import javax.persistence.metamodel.CollectionAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.5.2.v20140319-rNA", date="2014-10-15T01:08:56")
@StaticMetamodel(Sprinkler.class)
public class Sprinkler_ { 

    public static volatile CollectionAttribute<Sprinkler, Sprinklersetting> sprinklersettingCollection;
    public static volatile SingularAttribute<Sprinkler, String> controlmode;
    public static volatile SingularAttribute<Sprinkler, String> sprinklerid;
    public static volatile SingularAttribute<Sprinkler, String> status;
    public static volatile SingularAttribute<Sprinkler, String> description;

}