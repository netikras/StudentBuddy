package com.netikras.studies.studentbuddy.api.maps.controller;

import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingFloorDto;
import com.netikras.studies.studentbuddy.core.data.api.dto.location.BuildingSectionDto;
import com.netikras.studies.studentbuddy.core.data.api.model.BuildingFloor;
import com.netikras.tools.common.io.IoUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by netikras on 17.6.21.
 */
@RestController
@RequestMapping(value = "/map")
public class MapsController {



    @RequestMapping(
            value = "/school/{schoolId}" +
                    "/building/{buildingId}" +
                    "/section/{section}" +
                    "/floor/{floor}",
            method = RequestMethod.GET
    )
    public void getFloorMap(
            @PathVariable(name = "schoolId") String schoolId,
            @PathVariable(name = "buildingId") String buildingId,
            @PathVariable(name = "section") String buildingSection,
            @PathVariable(name = "floor") int floorNumber,
            HttpServletResponse response
    ) {
        BuildingFloor floor = null;

        writeOutBuildingFloorMap(floor, response);

    }


    @RequestMapping(
            value = "/school/{schoolId}" +
                    "/floorId/{floorId}",
            method = RequestMethod.GET
    )
    public void getFloorMap(
            @PathVariable(name = "schoolId") String schoolId,
            @PathVariable(name = "floorId") String floorId,
            HttpServletResponse response
    ) {

        BuildingFloor floor = null;

        writeOutBuildingFloorMap(floor, response);

    }

    @RequestMapping(
            value = "/floor/id/{id}/map/",
            method = RequestMethod.GET
    )
    public void getFloorMap(
            @RequestParam(name = "id") String floorId,
            HttpServletResponse response
    ) {
         BuildingFloor floor = null;
        writeOutBuildingFloorMap(floor, response);
    }


    @RequestMapping(
            value = "/school/{schoolId}/buildings",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<BuildingDto> getSchoolBuildings(
            @PathVariable(name = "schoolId") String schoolId
    ) {
        List<BuildingDto> buildings = null;

        return buildings;
    }

    @RequestMapping(
            value = "/school/{schoolId}" +
                    "/building/{buildingId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<BuildingSectionDto> getSchoolBuildingSections(
            @PathVariable(name = "schoolId") String schoolId,
            @PathVariable(name = "buildingId") String buildingId
    ) {
        // e.g. east-wing, blue-garden, etc.
        List<BuildingSectionDto> sections = null;

        return sections;
    }

    @RequestMapping(
            value = "/school/{schoolId}" +
                    "/building/{buildingId}" +
                    "/section/{sectionId}",
            method = RequestMethod.GET
    )
    @ResponseBody
    public List<BuildingFloorDto> getSchoolBuildingSectionFloors(
            @PathVariable(name = "schoolId") String schoolId,
            @PathVariable(name = "buildingId") String buildingId,
            @PathVariable(name = "sectionId") String sectionId
    ) {
        List<BuildingFloorDto> floors = null;

        return floors;
    }





    private void writeOutBuildingFloorMap(BuildingFloor floor, HttpServletResponse response) {
        try {
            InputStream mapInputStream = floor.getLayouts().get(0).getFloorMap().getBinaryStream();
            IoUtils.copy(mapInputStream, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
            // todo throw custom exc
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
