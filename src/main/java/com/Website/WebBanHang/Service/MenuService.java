package com.Website.WebBanHang.Service;


import com.Website.WebBanHang.model.Menu;
import com.Website.WebBanHang.repository.MenuRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MenuService {

    private final MenuRepository menuRepository;
    public List<Menu> findAllMenu()
    {
        return menuRepository.findByHideTrueOrderByOrderAsc();
    }
    public Optional<Menu> fintById(int id){
        return menuRepository.findById(id);
    }
    public Menu save(Menu menu){
        return menuRepository.save(menu);
    }
    public void deleteById(int id){
        menuRepository.deleteById(id);
    }
}
