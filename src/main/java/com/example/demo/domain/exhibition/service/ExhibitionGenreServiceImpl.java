package com.example.demo.domain.exhibition.service;

import com.example.demo.domain.exhibition.converter.ExhibitionConverter;
import com.example.demo.domain.exhibition.dto.ExhibitionResponseDto;
import com.example.demo.domain.exhibition.entity.Exhibition;
import com.example.demo.domain.exhibition.repository.ExhibitionGenreRepository;
import com.example.demo.global.resolver.memberInfo.MemberInfo;
import com.example.demo.global.resolver.memberInfo.MemberInfoDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class ExhibitionGenreServiceImpl implements ExhibitionGenreService {

    private final ExhibitionGenreRepository exhibitionGenreRepository;
    private final ExhibitionConverter exhibitionConverter;


    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getMediaExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> mediaExhibitionsPage = exhibitionGenreRepository.findMediaExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> mediaExhibitions = mediaExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return mediaExhibitions;
    }

    @Override
        public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getCraftExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> craftExhibitionsPage = exhibitionGenreRepository.findCraftExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> craftExhibitions = craftExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return craftExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getDesignExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> designExhibitionsPage = exhibitionGenreRepository.findDesignExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> designExhibitions = designExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return designExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPictureExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> pictureExhibitionsPage = exhibitionGenreRepository.findPictureExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> pictureExhibitions = pictureExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return pictureExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSpecialExhibitionExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> specialExhibitionExhibitionsPage = exhibitionGenreRepository.findSpecialExhibitionExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> specialExhibitionExhibitions = specialExhibitionExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return specialExhibitionExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getSculptureExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> sculptureExhibitionsPage = exhibitionGenreRepository.findSculptureExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> sculptureExhibitions = sculptureExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return sculptureExhibitions;
    }
    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPlanExhibitionExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> planExhibitionExhibitionsPage = exhibitionGenreRepository.findPlanExhibitionExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> planExhibitionExhibitions = planExhibitionExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return planExhibitionExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getInstallationArtExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> installationArtExhibitionsPage = exhibitionGenreRepository.findInstallationArtExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> installationArtExhibitions = installationArtExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return installationArtExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getPaintingExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> paintingExhibitionsPage = exhibitionGenreRepository.findPaintingExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> paintingExhibitions = paintingExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return paintingExhibitions;
    }

    @Override
    public List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> getArtistExhibitionExhibitions(@MemberInfo MemberInfoDto memberInfoDto, int page) {
        Long memberId = memberInfoDto.getMemberId();
        int pageSize = 10;
        Pageable pageable = PageRequest.of(page - 1, pageSize);
        Page<Object[]> artistExhibitionExhibitionsPage = exhibitionGenreRepository.findArtistExhibitionExhibitions(memberId, pageable);

        List<ExhibitionResponseDto.ExhibitionGeneralResponseDto> artistExhibitionExhibitions = artistExhibitionExhibitionsPage.getContent()
                .stream()
                .map(array -> {
                    Exhibition exhibition = (Exhibition) array[0];
                    Boolean isLiked = (Boolean) array[1];
                    Boolean isScrapped = (Boolean) array[2];
                    return exhibitionConverter.convertToGeneralDto(exhibition, isLiked, isScrapped);
                })
                .collect(Collectors.toList());

        return artistExhibitionExhibitions;
    }
}