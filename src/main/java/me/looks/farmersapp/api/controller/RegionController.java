package me.looks.farmersapp.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.looks.farmersapp.dao.model.Region;
import me.looks.farmersapp.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Region", description = "Контроллер для выполнения операциями над записями о регионах")
@RequestMapping("/api/regions")
public class RegionController {

    private final RegionService regionService;

    @Autowired
    public RegionController(RegionService regionService) {
        this.regionService = regionService;
    }

    @Operation(summary = "Получить все регионы",
            description = "Возвращает список всех регионов, зарегистрированных в базе данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список всех регионов успешно получен"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос, невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при получении данных")
    })
    @GetMapping
    public ResponseEntity<List<Region>> getAllRegions(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Long code
    ) {
        return regionService.getAllRegions(name, code);
    }

    @Operation(summary = "Создать новую запись о регионе",
            description = "Создает новую запись региона в базе данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Запись о регионе успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос, невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при создании записи")
    })
    @PostMapping
    public ResponseEntity<Region> createRegion(@RequestBody Region region) {
        return regionService.createRegion(region);
    }

    @Operation(summary = "Обновить запись региона",
            description = "Обновляет данные существующей записи региона по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись о регионе успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Запись о регионе не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос, невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при обновлении записи")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Region> updateRegion(
            @Parameter(description = "ID записи региона, которую нужно обновить")
            @PathVariable Long id,
            @RequestBody Region region) {
        return regionService.updateRegion(id, region);
    }

    @Operation(summary = "Архивировать запись региона",
            description = "Архивирует существующую запись региона по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Запись о регионе успешно архивирована"),
            @ApiResponse(responseCode = "404", description = "Запись о регионе не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос, невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при архивировании записи")
    })
    @DeleteMapping("/{id}/archive")
    public ResponseEntity<Void> archiveRegion(
            @Parameter(description = "ID записи региона, которую нужно архивировать")
            @PathVariable Long id) {
        return regionService.archiveRegion(id);
    }
}
