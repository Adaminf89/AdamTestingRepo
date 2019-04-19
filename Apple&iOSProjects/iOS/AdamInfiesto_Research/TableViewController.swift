//
//  TableViewController.swift
//  AdamInfiesto_Research
//
//  Created by Adam Infiesto on 9/28/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import UIKit

class TableViewController: UITableViewController, UISearchResultsUpdating, UISearchBarDelegate {

    var newData = [News]()
    var filteredArray = [News]()
    var resualtCon = UITableViewController()
    var SearchController = UISearchController(searchResultsController:nil)
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Uncomment the following line to preserve selection between presentations
        // self.clearsSelectionOnViewWillAppear = false
        // Uncomment the following line to display an Edit button in the navigation bar for this view controller.
        // self.navigationItem.rightBarButtonItem = self.editButtonItem
        
       
       
        //navigationItem.largeTitleDisplayMode = .always
        
        topbarStuff()

        downParse(jsonAtUrl: "https://newsapi.org/v1/articles?source=bbc-news&sortBy=top&apiKey=40721cf13b214e7fb05e8c54df7e345a")
    }
    
    func topbarStuff()
    {
        navigationController?.navigationBar.prefersLargeTitles = true
        
        refreshControl = UIRefreshControl()
        refreshControl?.addTarget(self, action: #selector(self.reloadTable), for: .valueChanged)
        
        
        //search conroller to SHOW AND
        //update searches
        self.navigationItem.searchController?.searchResultsUpdater = self
        SearchController.searchResultsUpdater = self
        definesPresentationContext = true
        SearchController.dimsBackgroundDuringPresentation = false
        self.navigationItem.searchController = SearchController
    }
    //
    @objc func reloadTable()
    {
        tableView.reloadData()
        refreshControl?.endRefreshing()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        if isFiltering()
        {
            return filteredArray.count
        }
        return newData.count
    }

    func isFiltering() -> Bool {
        return SearchController.isActive && !searchbarisEmpty()
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "Cell_ID", for: indexPath) as? TableViewCell

        let post : News
        
        if isFiltering()
        {
            post = filteredArray[indexPath.row]
        }
        else
        {
            post = newData[indexPath.row]
        }
       
        // Configure the cell...
            cell?.daTitle.text = post.title
            cell?.subTitle.text = post.info

        return cell!
    }
    
   
    
    func updateSearchResults(for searchController: UISearchController)
    {
      
        
       filterContentForSearchText(searchController.searchBar.text!)
       
    }
        
    
    func searchbarisEmpty()-> Bool
    {
        return SearchController.searchBar.text?.isEmpty ?? true
    }
    
    func filterContentForSearchText(_ searchText: String, scope: String = "All")
    {
        filteredArray = newData.filter({(New : News) -> Bool in
            return New.title.lowercased().contains(searchText.lowercased())
        })
        
        tableView.reloadData()
    }

    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?)
    {
        
        if let indexPath = tableView.indexPathForSelectedRow
        {
            
            let dataSent = newData[indexPath.row]
            
            if let destination = segue.destination as? DetailViewController
            {
                destination.story = dataSent
            }
        }
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destinationViewController.
        // Pass the selected object to the new view controller.
    }
    */

}
