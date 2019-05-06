package circularOrbit;

import physicalObject.E1;

public class physicalShelfIterator implements physicalIterator
{
	private physicalShelf realPhysicalShelf;
	private int index;
    public physicalShelfIterator(physicalShelf physicalShelf) {    
        this.realPhysicalShelf = physicalShelf;    
        this.index = 0;    
    }    
    //检查是否还有下一个物体    
    public boolean hasNext() {    
        if(index < realPhysicalShelf.getLength()) {    
            return true;    
        }    
        else {    
            return false;    
        }    
    }    
    //返回指定位置的书籍    
    @Override
    public E1 next() {    
        E1 physical = realPhysicalShelf.getphysicalAt(index);    
        index ++;    
        return physical;    
    }    

}
