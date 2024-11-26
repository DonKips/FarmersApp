package me.looks.farmersapp.api.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import me.looks.farmersapp.dao.dto.FarmerDTO;
import me.looks.farmersapp.dao.model.Farmer;
import me.looks.farmersapp.service.FarmerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Tag(name = "Farmer", description = "Контроллер для выполнения операциями над записями о фермерах")
@RequestMapping("/api/farmers")
public class FarmerController {

    private final FarmerService farmerService;

    @Autowired
    public FarmerController(FarmerService farmerService) {
        this.farmerService = farmerService;
    }

    @Operation(summary = "Получить записи всех фермеров",
            description = "Возвращает список записей всех фермеров, зарегистрированных в базе данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Список всех записей фермеров успешно получен"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при получении данных")
    })
    @GetMapping
    public ResponseEntity<List<Farmer>> getAllFarmers(@RequestParam(required = false) String name,
                                                      @RequestParam(required = false) String inn,
                                                      @RequestParam(required = false) Boolean archived,
                                                      @RequestParam(required = false) Long registrationRegionId) {
        return farmerService.getAllFarmers(name, inn, archived, registrationRegionId);
    }

    @Operation(summary = "Получить запись фермера по ID",
            description = "Возвращает запись фермера по указанному ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись фермера успешно получена"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при получении данных")
    })
    @GetMapping("/{id}")
    public ResponseEntity<FarmerDTO> getFarmerById(@PathVariable Long id) {
        return farmerService.getFarmerById(id);
    }

    @Operation(summary = "Создать новую запись фермера",
            description = "Создает новую запись фермера в базе данных")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Запись о фермере успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос, невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при создании записи")
    })
    @PostMapping
    public ResponseEntity<Farmer> createFarmer(@RequestBody Farmer farmer) {
        return farmerService.createFarmer(farmer);
    }

    @Operation(summary = "Обновить запись фермера",
            description = "Обновляет данные существующей записи фермера по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Запись о фермере успешно обновлена"),
            @ApiResponse(responseCode = "404", description = "Запись о фермере не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос, невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при обновлении записи")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Farmer> updateFarmer(
            @Parameter(description = "ID записи фермера, которую нужно обновить")
            @PathVariable Long id,
            @RequestBody Farmer farmer) {
        return farmerService.updateFarmer(id, farmer);
    }

    @Operation(summary = "Архивировать запись фермера",
            description = "Архивирует существующую запись фермера по ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Запись о фермере успешно архивирована"),
            @ApiResponse(responseCode = "404", description = "Запись о фермере не найдена"),
            @ApiResponse(responseCode = "400", description = "Некорректный запрос, невалидные данные"),
            @ApiResponse(responseCode = "500", description = "Ошибка сервера при архивировании записи")
    })
    @DeleteMapping("/{id}/archive")
    public ResponseEntity<Void> archiveFarmer(
            @Parameter(description = "ID записи фермера, которую нужно архивировать")
            @PathVariable Long id) {
        return farmerService.archiveFarmer(id);
    }
}
