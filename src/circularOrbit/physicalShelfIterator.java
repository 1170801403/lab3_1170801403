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
    //����Ƿ�����һ������    
    public boolean hasNext() {    
        if(index < realPhysicalShelf.getLength()) {    
            return true;    
        }    
        else {    
            return false;    
        }    
    }    
    //����ָ��λ�õ��鼮    
    @Override
    public E1 next() {    
        E1 physical = realPhysicalShelf.getphysicalAt(index);    
        index ++;    
        return physical;    
    }    

}
