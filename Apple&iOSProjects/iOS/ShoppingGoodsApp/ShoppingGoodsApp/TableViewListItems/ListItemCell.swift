//
//  ListItemCell.swift
//  ShoppingGoodsApp
//
//  Created by Adam Infiesto on 4/17/19.
//  Copyright © 2019 Adam Infiesto. All rights reserved.
//

import UIKit
import Foundation

//cells are needed to populate a listview or collection view
//the cell represents each row in the tableview
class ListItemCell: UITableViewCell
{

    //string for id cell
    private static let cellID = "CellID"
    @IBOutlet weak var cardImage: UIImageView!
    @IBOutlet weak var cardName: UILabel!
    @IBOutlet weak var cardType: UILabel!
    

    override func awakeFromNib()
    {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool)
    {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
