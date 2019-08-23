package com.kakaopay.region.controller;

import com.kakaopay.region.model.input.RegionSupportRequest;
import com.kakaopay.region.model.output.LoadCsvResponse;
import com.kakaopay.region.model.output.RegionSupportResponse;
import com.kakaopay.region.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/region/v1")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @RequestMapping(value = "/upload/save", method = RequestMethod.POST)
    public LoadCsvResponse loadFileAndSaveAll(@RequestParam("file") MultipartFile multipartFile) {
        return regionService.loadFileAndSaveAll(multipartFile);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public List<RegionSupportResponse> getRegionSupportList() {
        return regionService.getRegionSupportList();
    }

    @RequestMapping(value = "/{regionName}", method = RequestMethod.GET)
    public RegionSupportResponse getRegionSupport(@PathVariable(name = "regionName") String regionName){
        return regionService.getRegionSupportResponse(regionName);
    }

    @RequestMapping(value = "/{regionName}", method = RequestMethod.PUT)
    public RegionSupportResponse updateRegionSupport(@PathVariable(name = "regionName") String regionName,
                                                     RegionSupportRequest regionSupportRequest){
        return regionService.updateRegionSupport(regionName, regionSupportRequest);
    }

    @RequestMapping(value = "/limit/count/sort", method = RequestMethod.GET)
    public List<String> getRegionNameListBySorted(@RequestParam(name = "limitCount") Integer count){
        return regionService.getRegionNameListBySorted(count);
    }

    @RequestMapping(value = "/min/rate", method = RequestMethod.GET)
    public String getMinAvgRate(){
        return regionService.getMinAvgRate();
    }
}
