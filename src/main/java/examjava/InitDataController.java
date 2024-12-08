package examjava;

import examjava.product.Product;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/initData")
public class InitDataController {

    private final InitData initData;

    @Autowired
    public InitDataController(InitData initData) {
        this.initData = initData;
    }

    @GetMapping
    public ResponseEntity<String> getTestData(){
        initData.createData();
        return new ResponseEntity<>("Initial Data added for testing", HttpStatus.OK);
    }




}
