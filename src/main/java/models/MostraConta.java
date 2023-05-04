package models;

import java.util.Objects;
import java.util.function.Consumer;

public class MostraConta implements Consumer{
    @Override
    public void accept(Object o){
        System.out.println(o);
    }

}
