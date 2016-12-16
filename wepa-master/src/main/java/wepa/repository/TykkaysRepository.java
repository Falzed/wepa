
package wepa.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public class TykkaysRepository {
    
    @Autowired
    KuvaRepository kuvaRepository;
    
    @Autowired 
    TykkaysRepository tykkaysRepository;
    
    @RequestMapping(value = "pic/{id}/tykkaa", method = RequestMethod.POST)
    public String tykkaa(@PathVariable Long id) {
        //hae kuvan tykkäykset reposta ja lisää yksi
        return "redirect:/pics/pic/{id}";
    }
}
