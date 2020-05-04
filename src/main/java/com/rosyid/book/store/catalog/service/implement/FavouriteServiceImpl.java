package com.rosyid.book.store.catalog.service.implement;

import static com.rosyid.book.store.catalog.util.FavouriteModelMapper.constructModel;

import com.rosyid.book.store.account.entity.User;
import com.rosyid.book.store.account.repository.UserRepository;
import com.rosyid.book.store.catalog.entity.Favourite;
import com.rosyid.book.store.catalog.entity.FavouriteDetail;
import com.rosyid.book.store.catalog.entity.Product;
import com.rosyid.book.store.catalog.payload.request.FavouriteRequest;
import com.rosyid.book.store.catalog.payload.response.FavouriteResponse;
import com.rosyid.book.store.catalog.persistence.CatalogEntityPersistence;
import com.rosyid.book.store.catalog.repository.FavouriteDetailRepository;
import com.rosyid.book.store.catalog.repository.FavouriteRepository;
import com.rosyid.book.store.catalog.repository.ProductRepository;
import com.rosyid.book.store.catalog.service.FavouriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpServerErrorException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Service
public class FavouriteServiceImpl implements FavouriteService
{
    @Autowired
    private FavouriteRepository favouriteRepository;

    @Autowired
    private FavouriteDetailRepository favouriteDetailRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;




    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FavouriteResponse saveOrUpdate(FavouriteRequest request) {
        // validate user
        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "User with id: " + request.getUserId() + " not found");

        Favourite favourite = favouriteRepository.findByUserId(user.getId());

        Set<FavouriteDetail> favouriteBookDetails = new HashSet<>();
        // initialize
        if (favourite == null) {
            favourite = new Favourite();
            favourite.setUser(user);
            favourite = favouriteRepository.save(favourite);

            // validate book
            Product product = productRepository.findById(request.getProductId()).orElse(null);
            if (product == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book with id: " + request.getProductId() + " not found");

            // detail
            favouriteBookDetails.add(saveFavouriteDetail(favourite, product));
        } else {
            // update
            Product product = productRepository.findById(request.getProductId()).orElse(null);
            if (product == null)
                throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Book with id: " + request.getProductId() + " not found");

            List<FavouriteDetail> currentFavouriteBookDetails = favouriteDetailRepository.findByUserIdAndBookId(user.getId(), product.getId());
            if (currentFavouriteBookDetails == null || currentFavouriteBookDetails.size() == 0)
                favouriteBookDetails.add(saveFavouriteDetail(favourite, product));
        }
        favourite.setFavouriteDetails(favouriteBookDetails);
        return constructModel(favourite);
    }

    /**
     * Find By User Id
     * @param userId
     * @return
     */
    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public FavouriteResponse findByUserId(Long userId)
    {
        return constructModel(favouriteRepository.findByUserId(userId));
    }

    @Override
    public FavouriteResponse findByUsername(String username) {
        return null;
    }


    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<FavouriteResponse> findAll()
    {
        return constructModel(favouriteRepository.findAll());
    }

    @Override
    public FavouriteResponse saveOrUpdate(FavouriteResponse entity) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public FavouriteResponse findById(Long id)
    {
        return constructModel(favouriteRepository.findById(id).orElse(null));
    }

    @Override
    public FavouriteResponse delete(FavouriteResponse entity) {
        return null;
    }

    @Override
    public FavouriteResponse deleteById(Long aLong) {
        return null;
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Long countAll() {
        return favouriteRepository.count();
    }

    private FavouriteDetail saveFavouriteDetail(Favourite favourite, Product product) {
        FavouriteDetail favouriteBookDetail = new FavouriteDetail();
        favouriteBookDetail.setProduct(product);
        favouriteBookDetail.setFavourite(favourite);
        return favouriteDetailRepository.save(favouriteBookDetail);
    }

    @Override
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public FavouriteResponse deleteByFavouriteDetailId(Long detailId) {
        FavouriteDetail favouriteBookDetail = favouriteDetailRepository.findById(detailId).orElse(null);
        if (favouriteBookDetail == null)
            throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "Favourite Book Detail with id: " + detailId + " not found");
        favouriteBookDetail.setStatus(CatalogEntityPersistence.Status.INACTIVE);
        favouriteBookDetail = favouriteDetailRepository.save(favouriteBookDetail);
        return constructModel(favouriteBookDetail.getFavourite());
    }
}
