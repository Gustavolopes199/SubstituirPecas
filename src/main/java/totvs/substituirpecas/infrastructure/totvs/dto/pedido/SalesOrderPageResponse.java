package totvs.substituirpecas.infrastructure.totvs.dto.pedido;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.util.List;

public record SalesOrderPageResponse(
        Integer count,
        Integer totalPages,
        Boolean hasNext,
        Long totalItems,
        List<Order> items
) {
    public record Order(
            Integer branchCode,
            Integer orderCode,
            String orderId,
            String customerOrderCode,
            LocalDateTime insertDate,
            String integrationCode,
            Long guideCode,
            String guideCpfCnpj,
            Long sellerCode,
            String sellerCpfCnpj,
            LocalDate orderDate,
            LocalDateTime maxChangeFilterDate,
            LocalDate arrivalDate,
            Long customerCode,
            String customerCpfCnpj,
            String customerName,
            Long representativeCode,
            String representativeCpfCnpj,
            String representativeName,
            Long operationCode,
            String operationName,
            Long paymentConditionCode,
            String paymentConditionName,
            BigDecimal quantity,
            BigDecimal grossValue,
            BigDecimal discountValue,
            BigDecimal netValue,
            String freightType,
            BigDecimal freightValue,
            Long shippingCompanyCode,
            String shippingCompanyCpfCnpj,
            String shippingCompanyName,
            String redispatchingFreightType,
            Long redispatchingShippingCompanyCode,
            String redispatchingShippingCompanyCpfCnpj,
            String redispatchingShippingCompanyName,
            BigDecimal totalAmountOrder,
            String statusOrder,
            Long reasonBlockingCode,
            String reasonBlockingDescription,
            Long reasonCancellationCode,
            String reasonCancellationDescription,
            Long omniSiteId,
            BigDecimal additionalValue,
            String experienceType,
            Boolean hasPdvTransaction,
            Boolean hasFinancialProcessed,
            Long priceTableCode,
            String shippingService,
            String shippingServiceName,
            LocalDateTime cancellationLimitDate,
            LocalDate billingForecastDate,
            LocalDateTime billingLimitDate,
            LocalDateTime lastBillingDate,
            LocalDateTime paymentBaseDate,
            String batchOrder,
            Long rollMeasureCode,
            String rollMeasureDescription,
            String ecommerceStage,
            List<Item> items,
            List<Invoice> invoices,
            ShippingAddress shippingAddress,
            List<Observation> observations,
            List<Observation> invoiceObservations,
            List<RepresentativeObservation> representativeObservations,
            List<Classification> classifications,
            List<Discount> discounts,
            List<Commissioned> commissioneds,
            List<Count> counts
    ) {}
    public record Item(
            Integer productCode,
            String name,
            String referenceCode,
            String referenceName,
            String productSku,
            String colorCode,
            String colorName,
            String sizeName,
            Integer quantity,
            BigDecimal toSettleQuantity,
            BigDecimal settledQuantity,
            Integer canceledQuantity,
            BigDecimal extraQuantity,
            Integer pendingQuantity,
            BigDecimal originalPrice,
            BigDecimal price,
            BigDecimal discountPercentage,
            Long lastChangeUserInsert,
            LocalDateTime lastChangeDate,
            LocalDate billingForecastDate,
            LocalDateTime insertDate,
            LocalDateTime cancellationLimitDate,
            LocalDateTime billingLimitDate,
            LocalDateTime lastBillingDate,
            Long rollMeasureCode,
            String rollMeasureDescription,
            Long customerOrderCode,
            String customerProductCode,
            List<BatchItem> batchItems
    ) {}

    public record BatchItem(
            Integer sequence,
            Integer branchCode,
            Long code,
            BigDecimal quantity
    ) {}

    public record Invoice(
            String accessKey,
            Long code,
            String serial,
            LocalDateTime issueDate,
            String status,                     // "Canceled" | ...
            String shippingCompanyName,
            Integer packageNumber,
            BigDecimal grossWeight,
            BigDecimal netWeight,
            String trackingCode,
            BigDecimal discountPercentage,
            BigDecimal quantity,
            BigDecimal productValue,
            BigDecimal additionalValue,
            BigDecimal shippingValue,

            @JsonProperty("InsuranceValue")
            BigDecimal insuranceValue,

            BigDecimal ipiValue,
            BigDecimal totalValue,
            Integer transactionBranchCode,
            LocalDateTime transactionDate,
            Long transactionCode,
            ElectronicInvoice electronic
    ) {}

    public record ElectronicInvoice(
            String accessKey,
            String electronicInvoiceStatus,
            Long receipt,
            LocalDateTime receivementDate
    ) {}

    public record ShippingAddress(
            String addressType,
            String publicPlace,
            String address,
            Integer number,
            String complement,
            Integer addressSequence,
            String neighborhood,
            Long ibgeCityCode,
            String cityName,
            String stateAbbreviation,
            String cep,
            Long bcbCountryCode,
            String countryName,
            Long postOfficeBox,
            String reference
    ) {}

    public record Observation(String observation) {}

    public record RepresentativeObservation(
            String observation,
            String visualizationType
    ) {}

    public record Classification(
            Long typeCode,
            String typeName,
            String code,
            String name
    ) {}

    public record Discount(
            Long typeDiscountCode,
            String description,
            BigDecimal discountPercentage,
            BigDecimal discountValue
    ) {}

    public record Commissioned(
            Long representativeCode,
            String representativeName,
            BigDecimal commissionPercentageBilling,
            BigDecimal commissionPercentageReceipt,
            String commissionedType
    ) {}

    public record Count(
            Integer branchCode,
            Long code,
            String description,
            String statusCount,
            Long packagingTypeCode,
            String packagingTypeDescription,
            Long separator,
            String separatorName
    ) {}
}
