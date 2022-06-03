package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@RequestMapping("/basic/items")
@RequiredArgsConstructor // itemRepository @Autowired 생성자 자동 주입
public class BasicItemController {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model){
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "basic/item";
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

//    @PostMapping("/add")
    public String addItemV1(@RequestParam String itemName,
                       @RequestParam Integer price,
                       @RequestParam Integer quantity,
                       Model model){

        Item item = new Item();
        item.setItemName(itemName);
        item.setPrice(price);
        item.setQuantity(quantity);

        itemRepository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item){
        // @ModelAttribute 가 Item 객체를 자동으로 만들어주고 (setXxx 로 접근)
        // model.addAttribute 에도 객체를 추가함. ("item" 이라는 이름으로)
        // @ModelAttribute("item") Item item 와 같다. 파라미터를 지우면 클래스명의 첫글자를 소문자로 바꿔서 추가.
        itemRepository.save(item);
        return "basic/item";
    }

//    @PostMapping("/add")
    public String addItemV3(Item item){
        // 직접 만든 객체 타입은 자동으로 @ModelAttribute 붙음
        itemRepository.save(item);
        // 새로고침은 마지막에 한 요청(POST 과 데이터)이 다시 실행되는 것이다
        // 상품 저장후에 뷰 템플릿으로 이동하면, 새로고침 시 계속 값을 추가하게 된다.
        // return "basic/item";

        // PRG: post/redirect/get
        // 상품 상세 화면으로 redirect 하면 controller 가 다시 실행되는 것이기 때문에
        // 새로고침을 해도 post 가 시행되지 않는다.
        // 하지만 다음과 같이 더하면, 인코딩 문제가 있어서 RedirectAttribute 를 사용한다
        return "redirect:/basic/items/" + item.getId();
    }

    @PostMapping("/add")
    public String addItemV4(Item item, RedirectAttributes redirectAttributes){
        Item savedItem = itemRepository.save(item);
        // url path 에 있으면 치환이 되고
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        // 없으면 쿼리파라미터 ?status=true 로 붙는다.
        redirectAttributes.addAttribute("status", true);
        return "redirect:/basic/items/{itemId}";

    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model){
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
         return "basic/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String editItem(@PathVariable Long itemId, @ModelAttribute Item item){
        itemRepository.update(itemId, item);
        return "redirect:/basic/items/{itemId}";
    }

    /*
    테스트용 데이터 추가
     */
    @PostConstruct
    public void init(){
        itemRepository.save(new Item("itemA", 10000, 10));
        itemRepository.save(new Item("itemB", 20000, 20));
    }
}
