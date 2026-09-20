package com.jjimenez.prices.infrastructure.grpc;

import com.inditex.precio.infrastructure.inbound.grpc.generated.PrecioGrpcServiceGrpc;
import com.inditex.precio.infrastructure.inbound.grpc.generated.PrecioRequest;
import com.inditex.precio.infrastructure.inbound.grpc.generated.PrecioResponse;
import com.jjimenez.prices.application.usecase.GetApplicablePriceUseCase;
import com.jjimenez.prices.application.usecase.PriceCriteria;
import com.jjimenez.prices.domain.model.Price;
import com.jjimenez.prices.infrastructure.grpc.mapper.PriceGrpcMapper;
import io.grpc.stub.StreamObserver;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class PriceGrpc extends PrecioGrpcServiceGrpc.PrecioGrpcServiceImplBase {

  private final GetApplicablePriceUseCase getApplicablePriceUseCase;

  private final PriceGrpcMapper priceGrpcMapper;

  @Override
  public void consultarPrecio(PrecioRequest request,
      StreamObserver<PrecioResponse> responseObserver) {
    try {
      PriceCriteria priceCriteria = PriceCriteria.builder()
          .brandId(request.getBrandId())
          .productId(request.getProductId())
          .applicationDate(LocalDateTime.parse(request.getApplicationDate()))
          .build();

      Price price = this.getApplicablePriceUseCase.getPrice(priceCriteria);

      PrecioResponse response = this.priceGrpcMapper.toResponse(price);

      responseObserver.onNext(response);
      responseObserver.onCompleted();

    } catch (Exception e) {
      responseObserver.onError(
          io.grpc.Status.INTERNAL
              .withDescription(e.getMessage())
              .asRuntimeException()
      );
    }
  }
}