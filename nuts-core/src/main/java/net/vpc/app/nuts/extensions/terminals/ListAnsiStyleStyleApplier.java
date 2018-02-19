/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.vpc.app.nuts.extensions.terminals;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author vpc
 */
class ListAnsiStyleStyleApplier implements AnsiStyleStyleApplier{
    List<AnsiStyleStyleApplier> suppliers=new ArrayList<AnsiStyleStyleApplier>();
    public ListAnsiStyleStyleApplier(List<AnsiStyleStyleApplier> suppliers) {
        this.suppliers=suppliers;
    }

    @Override
    public AnsiStyle apply(AnsiStyle old) {
        for (AnsiStyleStyleApplier supplier : suppliers) {
            old=supplier.apply(old);
        }
        return old;
    }
    
    
}
