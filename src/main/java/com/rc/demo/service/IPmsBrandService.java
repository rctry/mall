package com.rc.demo.service;

import com.rc.demo.mbg.model.PmsBrand;
import org.springframework.stereotype.Service;

import java.util.List;

public interface IPmsBrandService {
    List<PmsBrand> listAllBrand();

    int createBrand(PmsBrand brand);

    int updateBrand(Long id, PmsBrand brand);

    int deleteBrand(Long id);

    List<PmsBrand> listBrand(int pageNum, int pageSize);

    PmsBrand getBrand(Long id);
}