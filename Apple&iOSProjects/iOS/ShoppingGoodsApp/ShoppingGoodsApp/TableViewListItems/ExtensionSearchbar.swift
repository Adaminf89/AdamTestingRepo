//
//  ExtensionSearchbar.swift
//  ShoppingGoodsApp
//
//  Created by Adam Infiesto on 4/18/19.
//  Copyright © 2019 Adam Infiesto. All rights reserved.
//

import Foundation
import UIKit

extension TableViewListItem
{
    
    //search bar functionality
    //most of which is need
    //refer to apple documentation
    func topbarStuff()
    {
        //make the text large and small depending
        navigationController?.navigationBar.prefersLargeTitles = true
        
        //this is the pull down feature to refresh controller
        refreshControl = UIRefreshControl()
        refreshControl?.addTarget(self, action: #selector(self.reloadTable), for: .valueChanged)
        
        //search conroller to SHOW AND update searches
        self.navigationItem.searchController?.searchResultsUpdater = self
        SearchController.searchResultsUpdater = self
        definesPresentationContext = true
        SearchController.dimsBackgroundDuringPresentation = false
        self.navigationItem.searchController = SearchController
    }
    
    //filtering the entered text
    func filterContentForSearchText(_ searchText: String, scope: String = "All")
    {
        //since we have an empty array of data we will filter the array of cards that we took from the api
        //this will filter the two arrays with each other by name
            arrayOfNewData = arrayOfCards.filter({(card : Card) -> Bool in
            return card.name.lowercased().contains(searchText.lowercased())
        })
        //then we call the table view to reload
        tableView.reloadData()
    }
    
    //checks if the searchbar is empty
    func searchbarisEmpty()-> Bool
    {
        return SearchController.searchBar.text?.isEmpty ?? true
    }
    
    //funtion to see if we are using the searchbar at all
    func isFiltering() -> Bool
    {
        return SearchController.isActive && !searchbarisEmpty()
    }
    
    //needed obj-c fun to rreloace
    @objc func reloadTable()
    {
        tableView.reloadData()
        refreshControl?.endRefreshing()
    }
    // MARK: - Table view data source

}
