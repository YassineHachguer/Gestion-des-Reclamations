package yassine_hachguer.yassine_hachguer.web;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import yassine_hachguer.yassine_hachguer.entities.Reclamation;
import yassine_hachguer.yassine_hachguer.repository.ReclamationRepository;

@AllArgsConstructor
@Controller
public class ReclamationController {
    private ReclamationRepository reclamationRepository;
    @GetMapping("/user/index")
    public String index(Model model,
                        @RequestParam(name = "page", defaultValue = "0") int page,
                        @RequestParam(name = "size", defaultValue = "4") int size,
                        @RequestParam(name = "keyword", defaultValue = "") String keyword){

        Page<Reclamation> reclamationsPage = reclamationRepository.findByObjetContains(keyword, PageRequest.of(page,size));
        model.addAttribute("listeReclamations", reclamationsPage.getContent());
        model.addAttribute("pages",new int[reclamationsPage.getTotalPages()]);
        model.addAttribute("currentPage", page);
        model.addAttribute("keyword", keyword);
        return "reclamations";


    }
    @GetMapping("/admin/delete")
    public String delete(Long numero, String keyword, int page){
        reclamationRepository.deleteById(numero);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }
    @GetMapping("/admin/formReclamation")
    public String formReclamation(Model model){
        model.addAttribute("reclamation", new Reclamation());
        return "formReclamation";
    }

    @PostMapping(path = "/admin/save")
    public String save(@Valid Reclamation reclamation, BindingResult bindingResult, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "") String keyword){
        if(bindingResult.hasErrors()) return "formReclamation";
        reclamationRepository.save(reclamation);
        return "redirect:/user/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/admin/editReclamation")
    public String editReclamation(Model model, Long numero, String keyword, int page){

        Reclamation reclamation = reclamationRepository.findById(numero).orElse(null);
        if(reclamation==null) throw new RuntimeException("Reclamation Introuvable");
        model.addAttribute(reclamation);
        model.addAttribute("page", page);
        model.addAttribute("keyword", keyword);
        return "editReclamation";

    }
    @GetMapping("/admin/traiter")
    public String traiterReclamation(@RequestParam Long numero,
                                     @RequestParam(defaultValue = "") String keyword,
                                     @RequestParam(defaultValue = "0") int page) {
        Reclamation reclamation = reclamationRepository.findById(numero)
                .orElseThrow(() -> new RuntimeException("Reclamation introuvable"));
        reclamation.setTraitement(true);
        reclamationRepository.save(reclamation);
        return "redirect:/user/index?page=" + page + "&keyword=" + keyword;
    }

    @GetMapping("/")
    public String home() {
        return "redirect:/user/index";
    }
}
