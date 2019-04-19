//
//  CongressTableViewCell.swift
//  InfiestoAdam_CE5
//
//  Created by Adam Infiesto on 9/19/17.
//  Copyright © 2017 Adam Infiesto. All rights reserved.
//

import UIKit

class CongressTableViewCell: UITableViewCell {

    
    @IBOutlet weak var daName: UILabel!
    
    @IBOutlet weak var daTitle: UILabel!
    
    @IBOutlet weak var daParty: UILabel!
    
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }

}
